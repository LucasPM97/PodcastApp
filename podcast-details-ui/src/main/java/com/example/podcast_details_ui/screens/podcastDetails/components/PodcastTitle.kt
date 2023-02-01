package com.example.podcast_details_ui.screens.podcastDetails.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core_ui.theme.Green
import com.example.core_ui.theme.PodcastAppTheme

@Composable
fun PodcastTitle(
    podcastName: String,
    authorName: String? = null
) {
    Column {
        authorName?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.titleSmall,
                color = Green
            )
        }
        Text(
            text = podcastName,
            style = MaterialTheme.typography.titleLarge,
        )
    }
}

@Preview
@Composable
fun Preview_PodcastTitle() {
    PodcastAppTheme {
        PodcastTitle(
            podcastName = "This American Life",
            authorName = "Juan Menguez"
        )
    }
}

@Preview
@Composable
fun Preview_PodcastTitle_WithoutAuthorName() {
    PodcastAppTheme {
        PodcastTitle(
            podcastName = "This American Life"
        )
    }
}