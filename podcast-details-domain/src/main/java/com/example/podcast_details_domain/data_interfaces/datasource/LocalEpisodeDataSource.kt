package com.example.podcast_details_domain.data_interfaces.datasource

import com.example.core.models.Episode
import kotlinx.coroutines.flow.Flow

interface ILocalEpisodeDataSource {
    fun getEpisodesByPodcastFlow(podcastUuid: String): Flow<List<Episode>>
    suspend fun getEpisodesByPodcast(podcastUuid: String): List<Episode>
    suspend fun storeEpisodes(episodes: List<Episode>)
    suspend fun getEpisodesHistory(pageLimit: Int): List<Episode>
    suspend fun includeEpisodeToHistory(episodeUuid: String)
    suspend fun getEpisode(episodeUuid: String): Episode
}