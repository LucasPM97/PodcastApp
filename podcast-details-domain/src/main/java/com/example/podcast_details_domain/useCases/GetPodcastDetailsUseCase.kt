package com.example.podcast_details_domain.useCases

import com.example.podcast_details_domain.data_interfaces.repositories.IPodcastRepository
import com.example.podcast_details_domain.models.PodcastDetails
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetPodcastDetailsUseCase(
    private val repository: IPodcastRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) {

    suspend operator fun invoke(podcastUuid: String): PodcastDetails? = withContext(dispatcher) {

        val localPodcastDetails = repository.getLocalPodcastDetails(podcastUuid)

        if (localPodcastDetails != null) {
            return@withContext localPodcastDetails
        }

        val remotePodcastDetails = repository.getRemotePodcastDetails(podcastUuid)

        // TODO: Check if API calls returns a valid PodcastDetails
        if (remotePodcastDetails != null) {
            repository.storePodcastDetails(remotePodcastDetails)
        }

        return@withContext remotePodcastDetails
    }

}