package com.example.podcast_player_ui.components.rowPlayer

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Replay10
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun PlayerButtons(
    isPlaying: Boolean,
    onPlayClicked: (isPlaying: Boolean) -> Unit = {},
    onChangePosition: (secondsToMove: Int) -> Unit = {},
) {
    IconButton(onClick = {
        onChangePosition(-10)
    }) {
        Icon(
            imageVector = Icons.Filled.Replay10,
            contentDescription = "fav episode",
            tint = MaterialTheme.colorScheme.secondary
        )
    }
    IconButton(onClick = {
        onPlayClicked(!isPlaying)
    }) {
        Icon(
            imageVector = if (isPlaying) Icons.Filled.Pause
            else Icons.Filled.PlayArrow,
            contentDescription = "fav episode",
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}