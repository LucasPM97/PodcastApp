package com.example.podcast_details_domain.data_interfaces.datasource

import com.example.podcast_details_domain.models.PodcastDetails

interface ILocalPodcastDataSource {
    suspend fun getPodcastDetails(podcastUuid: String): PodcastDetails?
    suspend fun storePodcastDetails(podcast: PodcastDetails)
}