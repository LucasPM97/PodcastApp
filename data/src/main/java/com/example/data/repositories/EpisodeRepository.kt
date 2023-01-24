package com.example.data.repositories

import com.example.data.datasource.ILocalEpisodeDataSource
import com.example.data.datasource.ILocalPodcastDataSource

interface IEpisodeRepository {
    suspend fun getEpisodesHistory(pageLimit:Int)//Returns: Include Episode Data Class to
    suspend fun includeEpisodeToHistory()// Parameter: Include Episode Data Class
    suspend fun episodeWatched(episodeUuid: String): Boolean
}

class EpisodeRepository(
    private val localDataSource: ILocalEpisodeDataSource
) : IEpisodeRepository {
    override suspend fun getEpisodesHistory(pageLimit: Int) {
        return localDataSource.getEpisodesHistory(pageLimit)
    }

    override suspend fun includeEpisodeToHistory() {
        localDataSource.includeEpisodeToHistory(/*Include params*/)
    }

    override suspend fun episodeWatched(episodeUuid: String): Boolean {
        localDataSource.getEpisode(episodeUuid)
        //TODO: Check if episode is null
        return false
    }
}