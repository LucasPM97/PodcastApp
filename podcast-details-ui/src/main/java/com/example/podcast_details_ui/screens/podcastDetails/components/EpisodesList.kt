package com.example.podcast_details_ui.screens.podcastDetails.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core_ui.components.AppAsyncImage
import com.example.core_ui.extensions.roundedRectangle
import com.example.core_ui.theme.Gray
import com.example.core_ui.theme.PodcastAppTheme
import com.example.podcast_details_domain.extensions.datePublishedText
import com.example.podcast_details_domain.extensions.durationText
import com.example.podcast_details_domain.extensions.fullWatched
import com.example.podcast_details_domain.mocks.mockEpisodesList
import com.example.podcast_details_domain.models.Episode

@Composable
fun EpisodesList(
    episodes: List<Episode>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        items(episodes) {
            EpisodeItem(
                episode = it,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun EpisodeItem(
    episode: Episode,
    modifier: Modifier = Modifier
) {
    Row(
        modifier,
        verticalAlignment = Alignment.CenterVertically,

        ) {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AppAsyncImage(
                imageUrl = episode.imageUrl,
                contentDescription = "${episode.name}",
                modifier = Modifier
                    .size(80.dp)
                    .roundedRectangle(20.dp)
            )
            Spacer(modifier = Modifier.width(20.dp))
            Column {
                Text(
                    text = episode.name ?: "",
                    style = MaterialTheme.typography.bodyMedium,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "${episode.datePublishedText()} · ${episode.durationText()}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Gray
                )
            }
        }
        Spacer(modifier = Modifier.width(20.dp))
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Filled.PlayCircle,
                contentDescription = "play episode ${episode.name}",
                tint = if (episode.fullWatched()) Gray
                else Color.White
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