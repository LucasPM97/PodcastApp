package com.example.podcast_details_ui.screens.podcastDetails.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core_ui.extensions.roundedRectangle
import com.example.core_ui.theme.Gray
import com.example.core_ui.theme.PodcastAppTheme
import com.example.podcast_details_domain.extensions.datePublishedText
import com.example.podcast_details_domain.extensions.durationText
import com.example.podcast_details_domain.extensions.fullWatched
import com.example.podcast_details_domain.mocks.mockEpisode
import com.example.podcast_details_domain.models.Episode

@Composable
fun EpisodeItem(
    episode: Episode,
    height: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier
            .height(height.dp),
        verticalAlignment = Alignment.CenterVertically,

        ) {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            EpisodeImage(
                name = episode.name,
                imageUrl = episode.imageUrl,
                episodeDuration = episode.duration,
                timeWatched = episode.timeWatched,
                size = height,
                modifier = Modifier
                    .roundedRectangle(20.dp)
            )
            Spacer(modifier = Modifier.width(20.dp))
            Title(episode)
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

@Composable
private fun Title(episode: Episode) {
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

@Composable
@Preview
fun Preview_EpisodeItemFullWatched() {
    PodcastAppTheme {
        EpisodeItem(
            episode = mockEpisode().copy(
                duration = 3000,
                watched = true,
                timeWatched = 3000
            ),
            height = 80
        )
    }
}

@Composable
@Preview
fun Preview_EpisodeItemPartiallyWatched() {
    PodcastAppTheme {
        EpisodeItem(
            episode = mockEpisode().copy(
                duration = 3000,
                watched = true,
                timeWatched = 1500
            ),
            height = 80
        )
    }
}

@Composable
@Preview
fun Preview_EpisodeItemNoWatched() {
    PodcastAppTheme {
        EpisodeItem(
            episode = mockEpisode().copy(
                duration = 2964,
                watched = false,
                timeWatched = 0
            ),
            height = 80
        )
    }
}