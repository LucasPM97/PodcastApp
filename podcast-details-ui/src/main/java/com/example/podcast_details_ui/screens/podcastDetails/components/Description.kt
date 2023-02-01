package com.example.podcast_details_ui.screens.podcastDetails.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.core_ui.components.ExpandableText

@Composable
fun Description(description: String?) {
    description?.let {
        ExpandableText(
            text = description,
            collapsedMaxLine = 3,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}