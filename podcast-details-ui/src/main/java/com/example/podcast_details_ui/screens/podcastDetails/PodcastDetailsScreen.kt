package com.example.podcast_details_ui.screens.podcastDetails

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core_ui.theme.PodcastAppTheme
import com.example.podcast_details_domain.mocks.mockPodcast
import com.example.podcast_details_ui.screens.podcastDetails.components.*
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
    val scrollState = rememberLazyListState()

    val showHeaderTitle by remember {
        mutableStateOf(false)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.primary
    ) {
        Column {
            Header(
                modifier = Modifier.fillMaxWidth(),
                title = if (showHeaderTitle) state.podcastDetails?.name
                else null
            )

            if (state.isLoading) {
                //TODO: Show loading
            } else if (state.podcastDetails != null) {

                PodcastDetailsContent(
                    podcastDetails = state.podcastDetails,
                    scrollState = scrollState,
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