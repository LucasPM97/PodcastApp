package com.example.podcast_details_ui.screens.podcastDetails.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.core_ui.components.AppAsyncImage
import com.example.core_ui.extensions.roundedRectangle

@Composable
fun PodcastImage(imageUrl: String?, podcastName: String?) {
    AppAsyncImage(
        modifier = Modifier
            .fillMaxWidth()
            .height(360.dp)
            .roundedRectangle(20.dp),
        imageUrl = imageUrl ?: "",
        contentDescription = podcastName ?: ""
    )
}