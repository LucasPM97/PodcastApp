package com.example.podcast_details_domain.useCases

import com.example.podcast_details_domain.data_interfaces.repositories.IEpisodeRepository
import com.example.podcast_details_domain.data_interfaces.repositories.IPodcastRepository
import com.example.podcast_details_domain.models.ApiResponse
import com.example.podcast_details_domain.models.PodcastDetails
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetPodcastDetailsUseCase(
    private val podcastRepository: IPodcastRepository,
    private val episodeRepository: IEpisodeRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) {

    suspend operator fun invoke(podcastUuid: String): PodcastDetails? = withContext(dispatcher) {

        val localPodcastDetails: PodcastDetails? = null
        podcastRepository.getLocalPodcastDetails(podcastUuid)

        if (localPodcastDetails != null) {
            val podcastEpisodes = episodeRepository.getEpisodesByPodcast(podcastUuid)
            return@withContext localPodcastDetails.copy(
                episodes = podcastEpisodes
            )
        }

        val response = podcastRepository.getRemotePodcastDetails(podcastUuid)

        return@withContext when (response) {
            is ApiResponse.Success -> {
                response.data?.let { data ->
                    podcastRepository.storePodcastDetails(data)
                    data.episodes?.let {
                        episodeRepository.storeEpisodes(it)
                    }
                }
                response.data
            }
            is ApiResponse.Error -> {
                // Show error message
                null
            }
        }
    }

}