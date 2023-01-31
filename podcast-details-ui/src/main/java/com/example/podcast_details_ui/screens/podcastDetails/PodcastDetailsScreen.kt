package com.example.podcast_details_ui.screens.podcastDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.core_ui.theme.PodcastAppTheme
import com.example.podcast_details_domain.models.Episode
import com.example.podcast_details_domain.models.Genre
import com.example.podcast_details_domain.models.PodcastDetails
import com.example.podcast_details_ui.screens.podcastDetails.components.Header
import org.koin.androidx.compose.getViewModel
import kotlin.random.Random

@Composable
fun PodcastDetailsScreen(
    viewModel: PodcastDetailsViewModel = getViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    ScreenContent(state)
}

@Composable
fun ScreenContent(
    state: PodcastDetailsViewModel.PodcastDetailsUiState
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.primary
    ) {
        if (state.isLoading) {
            //TODO: Show loading
        } else if (state.podcastDetails != null) {
            PodcastDetailsContent(state.podcastDetails)
        } else {
            //TODO: Show error message
        }
    }


}

@Composable
fun PodcastDetailsContent(podcastDetails: PodcastDetails) {
    val showHeaderTitle by remember {
        mutableStateOf(true)
    }
    Column {
        Header(
            modifier = Modifier.fillMaxWidth(),
            title = if (showHeaderTitle) podcastDetails.name
            else null
        )
    }
}


@Preview
@Composable
fun Preview_ScreenContent() {
    PodcastAppTheme {
        ScreenContent(
            state = PodcastDetailsViewModel.PodcastDetailsUiState(
                podcastDetails = PodcastDetails(
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
                    episodes = listOf(
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
                )
            )
        )
    }
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