package com.example.podcast_details_domain.data_interfaces.datasource

import com.example.podcast_details_domain.models.PodcastDetails
import kotlinx.coroutines.flow.Flow

interface ILocalPodcastDataSource {
    fun getPodcastDetails(podcastUuid: String): Flow<PodcastDetails?>
    suspend fun storePodcastDetails(podcast: PodcastDetails)
}