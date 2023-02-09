package com.example.podcast_player_ui.components.rowPlayer

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.core.models.Episode
import com.example.core_ui.theme.Green

@Composable
fun EpisodeTitle(episode: Episode?) {
    Column {
        Text(
            text = episode?.name ?: "",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.secondary,
            maxLines = 1
        )
        Text(
            text = episode?.podcastName ?: "",
            style = MaterialTheme.typography.bodySmall,
            color = Green,
            maxLines = 1
        )
    }
}