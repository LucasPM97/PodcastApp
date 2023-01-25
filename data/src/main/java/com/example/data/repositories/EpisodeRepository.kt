package com.example.data.repositories

import com.example.podcast_details_domain.data_interfaces.datasource.ILocalEpisodeDataSource
import com.example.podcast_details_domain.data_interfaces.repositories.IEpisodeRepository
import com.example.podcast_details_domain.models.Episode

class EpisodeRepository(
    private val localDataSource: ILocalEpisodeDataSource
) : IEpisodeRepository {
    override suspend fun getEpisodesHistory(pageLimit: Int): List<Episode> {
        return localDataSource.getEpisodesHistory(pageLimit)
    }

    override suspend fun includeEpisodeToHistory(episodeUuid: String) {
        localDataSource.includeEpisodeToHistory(episodeUuid)
    }

    override suspend fun episodeWatched(episodeUuid: String): Boolean {
        localDataSource.getEpisode(episodeUuid)
        //TODO: Check if episode is null
        return false
    }
}