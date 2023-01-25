package com.example.podcast_details_domain.useCases

import com.example.podcast_details_domain.data_interfaces.repositories.IEpisodeRepository
import com.example.podcast_details_domain.data_interfaces.repositories.IPodcastRepository
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

        val localPodcastDetails = podcastRepository.getLocalPodcastDetails(podcastUuid)

        if (localPodcastDetails != null) {
            return@withContext localPodcastDetails.copy(
                episodes = episodeRepository.getEpisodesByPodcast(podcastUuid)
            )
        }

        val remotePodcastDetails = podcastRepository.getRemotePodcastDetails(podcastUuid)

        // TODO: Check if API calls returns a valid PodcastDetails
        if (remotePodcastDetails != null) {
            podcastRepository.storePodcastDetails(remotePodcastDetails)
            remotePodcastDetails.episodes?.let {
                episodeRepository.storeEpisodes(it)
            }
        }

        return@withContext remotePodcastDetails
    }

}