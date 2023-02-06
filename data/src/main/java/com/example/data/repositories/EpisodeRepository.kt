package com.example.data.repositories

import com.example.core.data_interfaces.datasource.ILocalEpisodeDataSource
import com.example.core.data_interfaces.repositories.IEpisodeRepository
import com.example.core.models.Episode
import kotlinx.coroutines.flow.Flow

class EpisodeRepository(
    private val localDataSource: ILocalEpisodeDataSource
) : IEpisodeRepository {
    override fun getEpisodesByPodcastFlow(podcastUuid: String): Flow<List<Episode>> {
        return localDataSource.getEpisodesByPodcastFlow(podcastUuid)
    }

    override fun getEpisodeByUuidFlow(uuid: String): Flow<Episode?> =
        localDataSource.getEpisodeByUuidFlow(uuid)

    override suspend fun getEpisodesByPodcast(podcastUuid: String): List<Episode> {
        return localDataSource.getEpisodesByPodcast(podcastUuid)
    }

    override suspend fun storeEpisodes(episodes: List<Episode>) {
        localDataSource.storeEpisodes(episodes)
    }

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