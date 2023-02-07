package com.example.podcast_player_ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.mocks.mockEpisode
import com.example.core.models.Episode
import com.example.core_ui.components.AppAsyncImage
import com.example.core_ui.components.SpacerVertical20
import com.example.core_ui.extensions.roundedRectangle
import com.example.core_ui.theme.Green
import com.example.core_ui.theme.PodcastAppTheme

@Composable
fun FullScreenPlayer(
    episode: Episode?,
    modifier: Modifier = Modifier,
    collapsePlayer: () -> Unit = {}
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.primary
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            IconButton(onClick = collapsePlayer) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowDown,
                    contentDescription = "collapse fullscreen player"
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(horizontal = 50.dp)
                    .weight(1f)
            ) {
                AppAsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .roundedRectangle(10.dp),
                    imageUrl = episode?.imageUrl ?: "",
                    contentDescription = episode?.name ?: "",
                )
                SpacerVertical20()
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Spacer(modifier = Modifier.width(30.dp))
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.weight(1f),
                    ) {
                        Text(
                            text = episode?.name ?: "",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.secondary,
                            textAlign = TextAlign.Center,
                            maxLines = 1
                        )
                        Text(
                            text = episode?.podcastName ?: "",
                            style = MaterialTheme.typography.titleSmall,
                            color = Green,
                            textAlign = TextAlign.Center,
                            maxLines = 1

                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Filled.FavoriteBorder,
                            contentDescription = "fav episode",
                            Modifier.size(30.dp)
                        )
                    }
                }
                SpacerVertical20()
                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Filled.SkipPrevious,
                            contentDescription = "fav episode",
                            Modifier.size(40.dp)
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Filled.PlayArrow,
                            contentDescription = "fav episode",
                            Modifier.size(40.dp)
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Filled.SkipNext,
                            contentDescription = "fav episode",
                            Modifier.size(40.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview
private fun Preview_FullScreenPlayer() {
    PodcastAppTheme {
        FullScreenPlayer(
            episode = mockEpisode(),
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        )
    }
}