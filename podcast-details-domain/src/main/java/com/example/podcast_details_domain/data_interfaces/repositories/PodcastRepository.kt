package com.example.podcast_details_domain.data_interfaces.repositories

import com.example.core.models.ApiResponse
import com.example.podcast_details_domain.models.PodcastDetails
import kotlinx.coroutines.flow.Flow

interface IPodcastRepository {
    fun getLocalPodcastDetails(podcastUuid: String): Flow<PodcastDetails?>
    suspend fun getRemotePodcastDetails(podcastUuid: String): ApiResponse<PodcastDetails>
    suspend fun storePodcastDetails(podcast: PodcastDetails)
}