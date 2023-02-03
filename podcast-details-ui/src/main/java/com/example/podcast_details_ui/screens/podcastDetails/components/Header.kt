package com.example.podcast_details_ui.screens.podcastDetails.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.core_ui.theme.PodcastAppTheme

@Composable
fun Header(
    modifier: Modifier = Modifier,
    title: String? = null,
    showTitle: Boolean = false,
    tintColor: Color = MaterialTheme.colorScheme.secondary,
    backOnClick: () -> Unit = {},
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = backOnClick
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "navigate back",
                tint = tintColor
            )
        }
        AnimatedVisibility(visible = showTitle) {
            Text(
                text = title ?: "",
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleSmall,
                color = tintColor
            )
        }
    }
}

@Preview
@Composable
fun PreviewHeader() {
    PodcastAppTheme {
        Header(
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Preview
@Composable
fun PreviewHeaderWithTitle() {
    PodcastAppTheme {
        Header(
            modifier = Modifier.fillMaxWidth(),
            title = "This American Life",
        )
    }
}


@Preview
@Composable
fun PreviewHeaderWithLongTitle() {
    PodcastAppTheme {
        Header(
            modifier = Modifier.fillMaxWidth(),
            title = "This American Life long long long long long long long long title",
        )
    }
}