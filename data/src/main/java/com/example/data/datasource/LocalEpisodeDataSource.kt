package com.example.data.datasource

import com.example.podcast_details_domain.data_interfaces.datasource.ILocalEpisodeDataSource
import com.example.podcast_details_domain.models.Episode
import kotlin.random.Random

class LocalEpisodeDataSource : ILocalEpisodeDataSource {
    override suspend fun getEpisodesHistory(pageLimit: Int): List<Episode> {
        return mockWatchedEpisodeList()
    }

    override suspend fun includeEpisodeToHistory(episodeUuid: String) {
        //TODO: Implement Database INSET ITEM
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

    private fun mockEpisode(
        watched: Boolean = Random.nextBoolean()
    ) = Episode(
        podcastUuid = "8c9998d7-7114-4514-ab17-1a0ad05f73fc",
        uuid = "eb9d1c8f-58a4-4adb-a3d3-6bca573d31a2",
        name = "Case #1 Britney",
        imageUrl = null,
        datePublished = 1432958400,
        description = "The Case:  Andrea's a writer no one reads. Then she makes a shocking discovery. The Facts:  Mystery Show is produced by myself, Alex Blumberg, Melinda Shopsin and Eric Mennel. Producing help from Chris Neary and Wendy Dorr. Eli Horowitz is contributing ed",
        audioUrl = "https://traffic.megaphone.fm/GLT5025099642.mp3?updated=1511216902",
        duration = 2964,
        seasonNumber = null,
        episodeNumber = null,
        watched = watched,
        timeWatched = if (watched) (0..2964).random() else 0
    )
}

