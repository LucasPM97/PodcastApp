package com.example.core.data_interfaces.datasource

import com.example.core.models.podcastDetails.PodcastDetails
import kotlinx.coroutines.flow.Flow

interface ILocalPodcastDataSource {
    fun getPodcastDetails(podcastUuid: String): Flow<PodcastDetails?>
    suspend fun storePodcastDetails(podcast: PodcastDetails)
}