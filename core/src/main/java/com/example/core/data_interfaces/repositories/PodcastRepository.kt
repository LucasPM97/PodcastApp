package com.example.core.data_interfaces.repositories

import com.example.core.models.ApiResponse
import com.example.core.models.podcastDetails.PodcastDetails
import kotlinx.coroutines.flow.Flow

interface IPodcastRepository {
    fun getLocalPodcastDetails(podcastUuid: String): Flow<PodcastDetails?>
    suspend fun getRemotePodcastDetails(podcastUuid: String): ApiResponse<PodcastDetails>
    suspend fun storePodcastDetails(podcast: PodcastDetails)
}