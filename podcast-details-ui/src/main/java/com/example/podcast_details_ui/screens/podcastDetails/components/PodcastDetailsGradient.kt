package com.example.podcast_details_ui.screens.podcastDetails.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core_ui.theme.Green
import com.example.core_ui.theme.PodcastAppTheme

@Composable
fun PodcastDetailsGradient(
    isVisible: Boolean,
    colors: List<Color>,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(),
        exit = fadeOut()
    ) {
        Box(
            modifier = modifier
                .background(
                    Brush.verticalGradient(
                        colors
                    )
                )
        )
    }
}

@Preview
@Composable
fun Preview_PodcastDetailsGradient() {
    PodcastAppTheme {
        PodcastDetailsGradient(
            isVisible = true,
            colors = listOf(
                MaterialTheme.colorScheme.secondaryContainer,
                MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier.size(50.dp)
        )
    }
}