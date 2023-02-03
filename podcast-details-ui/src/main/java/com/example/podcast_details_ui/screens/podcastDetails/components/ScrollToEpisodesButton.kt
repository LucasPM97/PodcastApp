package com.example.podcast_details_ui.screens.podcastDetails.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

@Composable
fun ScrollToEpisodesButton(
    modifier: Modifier = Modifier,
    scrollState: LazyListState? = null,
    firstEpisodeItemIndex: Int? = null,
) {
    val coroutineScope = rememberCoroutineScope()
    fun handleScrollToEpisodeList() {
        coroutineScope.launch {
            scrollState?.animateScrollToItem(index = firstEpisodeItemIndex ?: 0)
        }
    }

    Row(
        modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.clickable {
                handleScrollToEpisodeList()
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