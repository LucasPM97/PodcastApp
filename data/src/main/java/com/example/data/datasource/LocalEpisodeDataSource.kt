package com.example.data.datasource

import com.example.podcast_details_domain.data_interfaces.datasource.ILocalEpisodeDataSource
import com.example.podcast_details_domain.mocks.mockEpisode
import com.example.podcast_details_domain.mocks.mockEpisodesList
import com.example.core.models.Episode

class LocalEpisodeDataSource : ILocalEpisodeDataSource {
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

