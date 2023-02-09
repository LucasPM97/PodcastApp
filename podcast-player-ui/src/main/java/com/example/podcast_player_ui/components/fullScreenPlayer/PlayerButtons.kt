package com.example.podcast_player_ui.components.fullScreenPlayer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PlayerButtons(
    modifier: Modifier = Modifier,
    isPlaying: Boolean,
    onPlayClicked: () -> Unit = {},
    onSkipPreviousClicked: () -> Unit = {},
    onSkipNextClicked: () -> Unit = {},
) {
    Row(
        modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = onSkipPreviousClicked) {
            Icon(
                imageVector = Icons.Filled.SkipPrevious,
                contentDescription = "fav episode",
                Modifier.size(40.dp),
                tint = MaterialTheme.colorScheme.secondary
            )
        }
        IconButton(onClick = onPlayClicked) {
            Icon(
                imageVector = if (isPlaying) Icons.Filled.Pause
                else Icons.Filled.PlayArrow,
                contentDescription = "fav episode",
                Modifier.size(40.dp),
                tint = MaterialTheme.colorScheme.secondary
            )
        }
        IconButton(onClick = onSkipNextClicked) {
            Icon(
                imageVector = Icons.Filled.SkipNext,
                contentDescription = "fav episode",
                Modifier.size(40.dp),
                tint = MaterialTheme.colorScheme.secondary
            )
        }
    }
}