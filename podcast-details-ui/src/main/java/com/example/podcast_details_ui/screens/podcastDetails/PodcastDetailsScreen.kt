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
import com.example.podcast_details_domain.mocks.mockPodcast
import com.example.podcast_details_ui.screens.podcastDetails.components.Header
import com.example.podcast_details_ui.screens.podcastDetails.components.LoadingScreen
import com.example.podcast_details_ui.screens.podcastDetails.components.PodcastDetailsContent
import com.example.podcast_details_ui.screens.podcastDetails.components.PodcastDetailsGradient
import org.koin.androidx.compose.getViewModel

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
        color = MaterialTheme.colorScheme.primary
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
                    title = state.podcastDetails?.name
                )

                if (state.isLoading) {
                    LoadingScreen()
                } else if (state.podcastDetails != null) {

                    PodcastDetailsContent(
                        podcastDetails = state.podcastDetails,
                        scrollState = scrollState,
                        imageLoaded = {
                            it.calculateDominantColor { color ->
                                dominantColor = color
                            }
                        },
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
            state = PodcastDetailsViewModel.PodcastDetailsUiState(
                podcastDetails = mockPodcast
            )
        )
    }
}