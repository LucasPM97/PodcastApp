package com.example.podcast_details_domain.data_interfaces.repositories

import com.example.podcast_details_domain.models.Episode

interface IEpisodeRepository {
    suspend fun getEpisodesHistory(pageLimit: Int): List<Episode>
    suspend fun includeEpisodeToHistory(episodeUuid: String)
    suspend fun episodeWatched(episodeUuid: String): Boolean
}