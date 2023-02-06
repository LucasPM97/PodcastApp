package com.example.podcast_details_ui.screens.podcastDetails.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core_ui.theme.PodcastAppTheme
import com.example.podcast_details_domain.mocks.mockEpisodesList
import com.example.core.models.Episode

@Composable
fun EpisodesList(
    episodes: List<Episode>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        items(episodes) {
            EpisodeItem(
                episode = it,
                modifier = Modifier.fillMaxWidth(),
                height = 80
            )
        }
    }
}

@Preview
@Composable
fun Preview_EpisodesList() {
    PodcastAppTheme {
        EpisodesList(
            episodes = mockEpisodesList(),
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        )
    }
}