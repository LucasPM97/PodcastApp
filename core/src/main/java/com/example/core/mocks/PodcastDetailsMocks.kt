package com.example.core.mocks

import com.example.core.models.Episode
import com.example.core.models.Genre
import com.example.core.models.podcastDetails.PodcastDetails
import kotlin.random.Random

val mockPodcast = PodcastDetails(
    uuid = "d682a935-ad2d-46ee-a0ac-139198b83bcc",
    name = "This American Life",
    description = "This American Life is a weekly public radio show, heard by 2.2 million people on more than 500 stations. Another 2.5 million people download the weekly podcast. It is hosted by Ira Glass, produced in collaboration with Chicago Public Media, delivered to stations by PRX The Public Radio Exchange, and has won all of the major broadcasting awards.",
    imageUrl = "https://files.thisamericanlife.org/sites/all/themes/thislife/img/tal-name-1400x1400.png",
    genres = listOf(
        Genre(
            genreText = "fiction",
            genreRemoteEnumString = "PODCASTSERIES_FICTION"
        )
    ),
    websiteUrl = null,
    authorName = "Juan Menguez",
    episodes = mockEpisodesList()
)

fun mockEpisodesList() = listOf(
    mockEpisode().copy(
        duration = 2964,
        seasonNumber = null,
        episodeNumber = null,
        timeWatched = 2964
    ),
    mockEpisode().copy(
        uuid = "eb9d1c8f-58a4-4adb-a3d3-6bca573d31a3",
        name = "Case #2 Britney",
        duration = 6000
    ),
    mockEpisode().copy(
        uuid = "eb9d1c8f-58a4-4adb-a3d3-6bca573d31a3",
        name = "Case #3 Britney"
    ),
    mockEpisode().copy(
        uuid = "eb9d1c8f-58a4-4adb-a3d3-6bca573d31a3",
        name = "Case #4 Britney"
    ),
    mockEpisode().copy(
        uuid = "eb9d1c8f-58a4-4adb-a3d3-6bca573d31a3",
        name = "Case #5 Britney",
        duration = 6000
    ),
    mockEpisode().copy(
        uuid = "eb9d1c8f-58a4-4adb-a3d3-6bca573d31a3",
        name = "Case #6 Britney"
    ),
    mockEpisode().copy(
        uuid = "eb9d1c8f-58a4-4adb-a3d3-6bca573d31a3",
        name = "Case #7 Britney"
    ),
    mockEpisode().copy(
        uuid = "eb9d1c8f-58a4-4adb-a3d3-6bca573d31a3",
        name = "Case #8 Britney",
        duration = 6000
    ),
    mockEpisode().copy(
        uuid = "eb9d1c8f-58a4-4adb-a3d3-6bca573d31a3",
        name = "Case #9 Britney"
    ),
    mockEpisode().copy(
        uuid = "eb9d1c8f-58a4-4adb-a3d3-6bca573d31a3",
        name = "Case #10 Britney"
    ),
    mockEpisode().copy(
        uuid = "eb9d1c8f-58a4-4adb-a3d3-6bca573d31a3",
        name = "Case #11 Britney",
        duration = 6000
    ),
    mockEpisode().copy(
        uuid = "eb9d1c8f-58a4-4adb-a3d3-6bca573d31a3",
        name = "Case #12 Britney"
    ),
)

fun mockEpisode(
    watched: Boolean = Random.nextBoolean()
) = Episode(
    podcastUuid = "8c9998d7-7114-4514-ab17-1a0ad05f73fc",
    uuid = "eb9d1c8f-58a4-4adb-a3d3-6bca573d31a2",
    name = "Case #1 Britney",
    imageUrl = "https://files.thisamericanlife.org/sites/all/themes/thislife/img/tal-name-1400x1400.png",
    datePublished = 1432958400,
    description = "The Case:  Andrea's a writer no one reads. Then she makes a shocking discovery. The Facts:  Mystery Show is produced by myself, Alex Blumberg, Melinda Shopsin and Eric Mennel. Producing help from Chris Neary and Wendy Dorr. Eli Horowitz is contributing ed",
    audioUrl = "https://traffic.megaphone.fm/GLT5025099642.mp3?updated=1511216902",
    duration = 2964,
    seasonNumber = null,
    episodeNumber = null,
    timeWatched = if (watched) (0..2964).random() else 0,
    podcastName = "This American Life"
)

