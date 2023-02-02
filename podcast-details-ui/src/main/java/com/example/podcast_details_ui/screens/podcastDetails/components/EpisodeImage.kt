package com.example.podcast_details_ui.screens.podcastDetails.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core_ui.components.AppAsyncImage
import com.example.core_ui.extensions.roundedRectangle
import com.example.core_ui.theme.Green
import com.example.core_ui.theme.PodcastAppTheme

@Composable
fun EpisodeImage(
    name: String?,
    imageUrl: String,
    episodeDuration: Int?,
    timeWatched: Int,
    modifier: Modifier = Modifier,
    size: Int
) {
    Box(
        modifier = modifier
    ) {
        AppAsyncImage(
            imageUrl = imageUrl,
            contentDescription = name ?: "episode",
            modifier = Modifier
                .size(size.dp)
                .roundedRectangle(20.dp)
        )
        if (episodeDuration != null) {
            TimeWatchedLine(
                episodeDuration,
                timeWatched,
                maxWidth = size,
                modifier = Modifier
                    .background(Green)
                    .align(Alignment.BottomStart)
            )
        }
    }
}

@Composable
private fun TimeWatchedLine(
    episodeDuration: Int,
    timeWatched: Int,
    maxWidth: Int,
    modifier: Modifier = Modifier
) {
    val watchedLineWidth = (timeWatched * maxWidth) / episodeDuration
    Box(
        modifier = modifier
            .height(4.dp)
            .width(watchedLineWidth.dp)
    )
}


@Composable
@Preview
fun Preview_EpisodeImageFullWatched() {
    PodcastAppTheme {
        EpisodeImage(
            name = "",
            imageUrl = "",
            episodeDuration = 3000,
            timeWatched = 3000,
            size = 80,
            modifier = Modifier
                .roundedRectangle(20.dp)
        )
    }
}

@Composable
@Preview
fun Preview_EpisodeImagePartiallyWatched() {
    PodcastAppTheme {
        EpisodeImage(
            name = "",
            imageUrl = "",
            episodeDuration = 3000,
            timeWatched = 1250,
            size = 80,
            modifier = Modifier
                .roundedRectangle(20.dp)
        )
    }
}

@Composable
@Preview
fun Preview_EpisodeImageNoWatched() {
    PodcastAppTheme {
        EpisodeImage(
            name = "",
            imageUrl = "",
            episodeDuration = 3000,
            timeWatched = 0,
            size = 80,
            modifier = Modifier
                .roundedRectangle(20.dp)
        )
    }
}