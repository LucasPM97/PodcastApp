package com.example.podcast_details_ui.screens.podcastDetails.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core_ui.components.fillLazyColumn
import com.example.core_ui.theme.PodcastAppTheme
import com.example.podcast_details_domain.mocks.mockPodcast
import com.example.podcast_details_domain.models.Episode
import com.example.podcast_details_domain.models.PodcastDetails
import kotlinx.coroutines.launch

@Composable
fun SpaceBetween() {
    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
fun PodcastDetailsContent(
    podcastDetails: PodcastDetails,
    modifier: Modifier = Modifier,
    scrollState: LazyListState = rememberLazyListState(),
) {
    val coroutineScope = rememberCoroutineScope()
    fun handleScrollToEpisodeList() {
        coroutineScope.launch {
            scrollState.animateScrollToItem(index = 2)
        }
    }

    var listHeightInPx by remember { mutableStateOf(0) }

    val episodeItemHeightInPx = LocalDensity.current.run { EPISODE_ITEM_SIZE_PLUS_SPACER_DP.toPx() }
    val firstEpisodeItemOffsetInPx =
        LocalDensity.current.run { FIRST_EPISODE_ITEM_OFFSET_DP.toPx() }

    LazyColumn(
        state = scrollState,
        modifier = modifier
            .onSizeChanged {
                listHeightInPx = it.height
            },
    ) {
        item {
            SpaceBetween()
            PodcastImage(
                imageUrl = podcastDetails.imageUrl,
                podcastName = podcastDetails.name
            )
            SpaceBetween()
            PodcastTitle(
                podcastName = podcastDetails.name ?: "",
                authorName = podcastDetails.authorName
            )
        }
        item {
            Description(podcastDetails.description)
            SpaceBetween()
            ActionButtons(
                lastEpisode = podcastDetails.episodes?.last(),
                fav = false,
                onFav = {

                },
                onPlayEpisode = {

                },
                modifier = Modifier.fillMaxWidth()
            )
            SpaceBetween()
            ScrollToEpisodesButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = { handleScrollToEpisodeList() }
            )
            SpaceBetween()
        }
        podcastDetails.episodes?.let { episodes ->
            renderLazyEpisodesList(episodes)
            fillLazyColumn(
                spaceAlreadyOccupiedInPx = episodeItemHeightInPx * episodes.size + firstEpisodeItemOffsetInPx,
                listHeightInPx = listHeightInPx.toFloat()
            )
        }

    }
}


const val EPISODE_ITEM_SIZE = 80
val FIRST_EPISODE_ITEM_OFFSET_DP = 10.dp
val EPISODE_ITEM_SIZE_PLUS_SPACER_DP = (EPISODE_ITEM_SIZE).dp + 20.dp

// Forced to do this because the NestedScroll on Compose doesn't work as it should
private fun LazyListScope.renderLazyEpisodesList(episodes: List<Episode>) {
    items(episodes) {
        EpisodeItem(
            episode = it,
            height = EPISODE_ITEM_SIZE,
            modifier = Modifier
                .fillMaxWidth()
        )
        SpaceBetween()
    }
}


@Preview
@Composable
fun Preview_PodcastDetailsContent() {
    PodcastAppTheme {
        PodcastDetailsContent(
            podcastDetails = mockPodcast,
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
        )
    }
}