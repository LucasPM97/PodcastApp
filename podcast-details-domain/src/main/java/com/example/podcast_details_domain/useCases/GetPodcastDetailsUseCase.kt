package com.example.podcast_details_domain.useCases

import com.example.core.data_interfaces.repositories.IEpisodeRepository
import com.example.core.data_interfaces.repositories.IPodcastRepository
import com.example.core.models.podcastDetails.PodcastDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class GetPodcastDetailsUseCase(
    private val podcastRepository: IPodcastRepository,
    private val episodeRepository: IEpisodeRepository,
) {
    operator fun invoke(podcastUuid: String): Flow<PodcastDetails?> =
        mergePodcastDetailsAndEpisodesLocalFlows(podcastUuid)


    private fun mergePodcastDetailsAndEpisodesLocalFlows(podcastUuid: String): Flow<PodcastDetails?> {
        val localPodcastDetailsFlow =
            podcastRepository.getLocalPodcastDetails(podcastUuid)
        val podcastEpisodesFlow = episodeRepository.getEpisodesByPodcastFlow(podcastUuid)

        return combine(
            localPodcastDetailsFlow,
            podcastEpisodesFlow
        ) { podcastDetails, episodes ->
            podcastDetails?.copy(
                episodes = episodes
            )
        }
    }
}

