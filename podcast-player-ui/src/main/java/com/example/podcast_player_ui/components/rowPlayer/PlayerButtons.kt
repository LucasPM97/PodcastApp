package com.example.podcast_player_ui.components.rowPlayer

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun PlayerButtons(
    isPlaying: Boolean,
    onPlayClicked: () -> Unit = {},
    onSkipNextClicked: () -> Unit = {},
) {
    IconButton(onClick = onPlayClicked) {
        Icon(
            imageVector = if (isPlaying) Icons.Filled.Pause
            else Icons.Filled.PlayArrow,
            contentDescription = "play",
            tint = MaterialTheme.colorScheme.secondary
        )
    }
    IconButton(onClick = onSkipNextClicked) {
        Icon(
            imageVector = Icons.Filled.SkipNext, contentDescription = "play",
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}