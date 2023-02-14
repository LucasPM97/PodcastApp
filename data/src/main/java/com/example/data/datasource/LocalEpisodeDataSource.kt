package com.example.data.datasource

import com.example.core.data_interfaces.datasource.ILocalEpisodeDataSource
import com.example.core.mocks.mockEpisode
import com.example.core.mocks.mockEpisodesList
import com.example.core.models.Episode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LocalEpisodeDataSource : ILocalEpisodeDataSource {
    override fun getEpisodesByPodcastFlow(podcastUuid: String): Flow<List<Episode>> = flow {
        emit(mockEpisodesList())
    }

    override fun getEpisodeByUuidFlow(uuid: String): Flow<Episode?> = flow {
        emit(
            mockEpisodesList().first {
                it.uuid == uuid
            }
        )
    }

    override suspend fun getEpisodesByPodcast(podcastUuid: String): List<Episode> {
        return mockEpisodesList()
    }

    override suspend fun storeEpisodes(episodes: List<Episode>) {
        //TODO: Implement Database INSERT List
    }

    override suspend fun getEpisodesHistory(pageLimit: Int): List<Episode> {
        return mockWatchedEpisodeList()
    }

    override suspend fun includeEpisodeToHistory(episodeUuid: String) {
        //TODO: Implement Database UPDATE ITEM
    }

    override suspend fun getEpisode(episodeUuid: String): Episode {
        //TODO: Implement Database GET ITEM
        return mockEpisode()
    }

    private fun mockWatchedEpisodeList(): List<Episode> {
        return listOf(
            mockEpisode(true),
            mockEpisode(true).copy(
                uuid = "eb9d1c8f-58a4-4adb-a3d3-6bca573d31a3",
                name = "Case #2 Britney"
            ),
            mockEpisode(true).copy(
                uuid = "eb9d1c8f-58a4-4adb-a3d3-6bca573d31a3",
                name = "Case #2 Britney"
            ),
        )
    }
}

