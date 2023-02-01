package com.example.podcast_details_ui.screens.podcastDetails.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core_ui.theme.PodcastAppTheme
import com.example.podcast_details_domain.mocks.mockEpisode
import com.example.podcast_details_domain.models.Episode

@Composable
fun ActionButtons(
    lastEpisode: Episode?,
    fav: Boolean,
    modifier: Modifier = Modifier,
    onPlayEpisode: (String) -> Unit = {},
    onFav: (Boolean) -> Unit = {}
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        PlayLastEpisodeButton(
            lastEpisode,
            onPlayEpisode = onPlayEpisode,
            modifier = Modifier.weight(1f),
        )
        ShareButton()
        Spacer(modifier = Modifier.width(30.dp))
        FavButton(onFav, fav)
    }
}

@Composable
private fun PlayLastEpisodeButton(
    lastEpisode: Episode?,
    modifier: Modifier = Modifier,
    onPlayEpisode: (String) -> Unit
) {
    if (lastEpisode?.audioUrl != null) {
        Button(
            onClick = {
                onPlayEpisode(lastEpisode.audioUrl!!)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            ),
            modifier = modifier
        ) {
            Text(
                text = "Play",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
        Spacer(modifier = Modifier.width(30.dp))
    }
}

@Composable
private fun ShareButton() {
    IconButton(onClick = {
        // TODO: Share this podcast using deeplinking or other way
    }) {
        Icon(
            imageVector = Icons.Filled.Share,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
private fun FavButton(onFav: (Boolean) -> Unit, fav: Boolean) {
    IconButton(onClick = {
        onFav(!fav)
    }) {
        Icon(
            imageVector = if (fav) Icons.Filled.Favorite
            else Icons.Filled.FavoriteBorder,
            contentDescription = "fav this podcast",
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@Preview
@Composable
fun Preview_ActionButtons() {
    PodcastAppTheme {
        ActionButtons(
            lastEpisode = mockEpisode(),
            fav = false,
            onFav = {

            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

