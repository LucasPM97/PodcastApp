package com.example.podcast_details_ui.screens.podcastDetails

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core_ui.extensions.calculateDominantColor
import com.example.core_ui.theme.PodcastAppTheme
import com.example.core.mocks.mockPodcast
import com.example.core.models.podcastDetails.PodcastDetails
import com.example.podcast_details_ui.screens.podcastDetails.components.Header
import com.example.podcast_details_ui.screens.podcastDetails.components.LoadingScreen
import com.example.podcast_details_ui.screens.podcastDetails.components.PodcastDetailsContent
import com.example.podcast_details_ui.screens.podcastDetails.components.PodcastDetailsGradient
import org.koin.androidx.compose.getViewModel

@Composable
fun PodcastDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: PodcastDetailsViewModel = getViewModel(),
    openPodcastPlayer: (episodeUuid: String) -> Unit
) {
    val state by viewModel.uiState.collectAsState()
    val podcastDetails by viewModel.podcastDetails.collectAsState(initial = null)

    ScreenContent(
        state,
        podcastDetails,
        modifier,
        openPodcastPlayer
    )
}

@Composable
fun ScreenContent(
    state: PodcastDetailsViewModel.PodcastDetailsUiState,
    podcastDetails: PodcastDetails?,
    modifier: Modifier = Modifier,
    openPodcastPlayer: (episodeUuid: String) -> Unit = {}
) {
    val primaryColor = MaterialTheme.colorScheme.primary
    var dominantColor by remember {
        mutableStateOf(primaryColor)
    }

    val scrollState = rememberLazyListState()

    val podcastTitleNotVisible by remember {
        derivedStateOf {
            scrollState.firstVisibleItemIndex > 0
        }
    }

    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            PodcastDetailsGradient(
                isVisible = podcastTitleNotVisible,
                colors = listOf(
                    dominantColor,
                    primaryColor
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )
            Column {
                Header(
                    modifier = Modifier.fillMaxWidth(),
                    showTitle = podcastTitleNotVisible,
                    title = podcastDetails?.name
                )

                if (state.isLoading) {
                    LoadingScreen()
                } else if (podcastDetails != null) {

                    PodcastDetailsContent(
                        podcastDetails = podcastDetails,
                        scrollState = scrollState,
                        imageLoaded = {
                            it.calculateDominantColor { color ->
                                dominantColor = color
                            }
                        },
                        openPodcastPlayer = openPodcastPlayer,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 20.dp)
                    )

                } else {
                    //TODO: Show error message
                }
            }
        }
    }
}

@Preview
@Composable
fun Preview_ScreenContent() {
    PodcastAppTheme {
        ScreenContent(
            state = PodcastDetailsViewModel.PodcastDetailsUiState(),
            podcastDetails = mockPodcast,
        )
    }
}