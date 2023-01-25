package com.example.podcast_details_domain.data_interfaces.datasource

import com.example.podcast_details_domain.models.Episode

interface ILocalEpisodeDataSource {
    suspend fun getEpisodesByPodcast(podcastUuid: String): List<Episode>
    suspend fun storeEpisodes(episodes: List<Episode>)
    suspend fun getEpisodesHistory(pageLimit: Int): List<Episode>
    suspend fun includeEpisodeToHistory(episodeUuid: String)
    suspend fun getEpisode(episodeUuid: String): Episode
}