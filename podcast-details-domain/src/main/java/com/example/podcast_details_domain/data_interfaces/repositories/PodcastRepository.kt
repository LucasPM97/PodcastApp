package com.example.podcast_details_domain.data_interfaces.repositories

import com.example.podcast_details_domain.models.ApiResponse
import com.example.podcast_details_domain.models.PodcastDetails

interface IPodcastRepository {
    suspend fun getLocalPodcastDetails(podcastUuid: String): PodcastDetails?
    suspend fun getRemotePodcastDetails(podcastUuid: String): ApiResponse<PodcastDetails>
    suspend fun storePodcastDetails(podcast: PodcastDetails)
}