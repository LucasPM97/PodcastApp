package com.example.podcast_player_ui.components.fullScreenPlayer

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
 fun CollapseButton(collapsePlayer: () -> Unit) {
    IconButton(onClick = collapsePlayer) {
        Icon(
            imageVector = Icons.Filled.KeyboardArrowDown,
            contentDescription = "collapse fullscreen player",
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}