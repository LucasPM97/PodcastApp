package com.example.podcast_player_ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.core.models.Episode
import com.example.core_ui.components.AppAsyncImage
import com.example.core_ui.components.SpacerHorizontal20
import com.example.core_ui.extensions.roundedRectangle
import com.example.podcast_player_ui.components.rowPlayer.EpisodeTitle
import com.example.podcast_player_ui.components.rowPlayer.PlayerButtons

// Vertical padding + component size
val ROW_PLAYER_HEIGHT = 120.dp

@Composable
fun RowPlayer(
    episode: Episode?,
    isPlaying: Boolean,
    modifier: Modifier = Modifier,
    expandPlayer: () -> Unit = {},
    onPlayPause: (isPlaying: Boolean) -> Unit = {},
    onChangePosition: (secondsToMove: Int) -> Unit = {}
) {
    Row(
        modifier = modifier.clickable {
            expandPlayer()
        },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppAsyncImage(
                modifier = Modifier
                    .size(70.dp)
                    .roundedRectangle(10.dp),
                imageUrl = episode?.imageUrl ?: "",
                contentDescription = episode?.name ?: "",
            )
            SpacerHorizontal20()
            EpisodeTitle(episode)
        }
        PlayerButtons(
            isPlaying = isPlaying,
            onPlayClicked = onPlayPause,
            onChangePosition = onChangePosition
        )
    }
}