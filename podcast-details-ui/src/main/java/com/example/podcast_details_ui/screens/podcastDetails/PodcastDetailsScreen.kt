package com.example.podcast_details_ui.screens.podcastDetails

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core_ui.theme.PodcastAppTheme
import com.example.podcast_details_domain.mocks.mockPodcast
import com.example.podcast_details_domain.models.Episode
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
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
            ) {
                item {
                    if (state.isLoading) {
                        //TODO: Show loading
                    } else if (state.podcastDetails != null) {
                        SpaceBetween()
                        PodcastDetailsContent(
                            podcastDetails = state.podcastDetails,
                        )
                    } else {
                        //TODO: Show error message
                    }
                }
                state.podcastDetails?.episodes?.let { episodes ->
                    LazyEpisodesList(episodes)
                }
            }
        }
    }
}

// Forced to do this because the NestedScroll on Compose doesn't work as it should
private fun LazyListScope.LazyEpisodesList(episodes: List<Episode>) {
    items(episodes) {
        EpisodeItem(
            episode = it,
            modifier = Modifier
                .fillMaxWidth()
        )
        SpaceBetween()
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