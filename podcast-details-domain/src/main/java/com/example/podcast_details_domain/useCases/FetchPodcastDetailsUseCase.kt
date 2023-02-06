package com.example.podcast_details_domain.useCases

import com.example.core.models.ApiResponse
import com.example.core.data_interfaces.repositories.IEpisodeRepository
import com.example.core.data_interfaces.repositories.IPodcastRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FetchPodcastDetailsUseCase(
    private val podcastRepository: IPodcastRepository,
    private val episodeRepository: IEpisodeRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) {

    suspend operator fun invoke(podcastUuid: String): ApiResponse<Nothing> =
        withContext(dispatcher) {

            val apiResponseSucceeded = fetchPodcastDetails(podcastUuid)
            return@withContext if (apiResponseSucceeded) {
                ApiResponse.Success(null)
            } else {
                ApiResponse.Error(null)
            }

        }

    private suspend fun fetchPodcastDetails(podcastUuid: String): Boolean {
        //TODO: Start using remote data
        return true
        val response = podcastRepository.getRemotePodcastDetails(podcastUuid)

        return when (response) {
            is ApiResponse.Success -> {
                response.data?.let { data ->
                    podcastRepository.storePodcastDetails(data)
                    data.episodes?.let {
                        episodeRepository.storeEpisodes(it)
                    }
                }
                true
            }
            is ApiResponse.Error -> {
                false
            }
        }
    }
}

