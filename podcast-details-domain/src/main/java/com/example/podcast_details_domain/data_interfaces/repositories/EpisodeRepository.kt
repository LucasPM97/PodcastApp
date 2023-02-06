package com.example.podcast_details_domain.data_interfaces.repositories

import com.example.core.models.Episode
import kotlinx.coroutines.flow.Flow

interface IEpisodeRepository {
    fun getEpisodesByPodcastFlow(podcastUuid: String): Flow<List<Episode>>
    suspend fun getEpisodesByPodcast(podcastUuid: String): List<Episode>
    suspend fun storeEpisodes(episodes: List<Episode>)
    suspend fun getEpisodesHistory(pageLimit: Int): List<Episode>
    suspend fun includeEpisodeToHistory(episodeUuid: String)
    suspend fun episodeWatched(episodeUuid: String): Boolean
}