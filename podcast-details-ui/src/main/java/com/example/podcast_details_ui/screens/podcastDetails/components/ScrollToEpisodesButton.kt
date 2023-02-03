package com.example.podcast_details_ui.screens.podcastDetails.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ScrollToEpisodesButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Row(
        modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.clickable {
                onClick()
            },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "See all episodes",
                style = MaterialTheme.typography.bodyMedium
            )
            Icon(
                imageVector = Icons.Filled.KeyboardArrowDown,
                contentDescription = "See all episodes"
            )
        }
    }
}