package com.example.podcast_details_ui.screens.podcastDetails.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

    var listHeight by remember { mutableStateOf(0) }

    val episodeItemHeightInPx = LocalDensity.current.run { EPISODE_ITEM_SIZE_PLUS_SPACER_DP.toPx() }
    val firstEpisodeItemOffsetInPx =
        LocalDensity.current.run { FIRST_EPISODE_ITEM_OFFSET_DP.toPx() }

    LazyColumn(
        state = scrollState,
        modifier = modifier
            .onSizeChanged {
                listHeight = it.height
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
            LazyEpisodesList(episodes)
            item {
                Spacer(
                    modifier = Modifier
                        .fillParentMaxHeight(
                            getNeededFractionToFillParent(
                                episodeItemHeightInPx = episodeItemHeightInPx,
                                episodesSize = episodes.size,
                                offset = firstEpisodeItemOffsetInPx,
                                listHeight = listHeight
                            )
                        )
                )
            }
        }

    }
}

fun getNeededFractionToFillParent(
    episodeItemHeightInPx: Float,
    episodesSize: Int,
    offset: Float,
    listHeight: Int
): Float {
    val episodesTotalHeightInPx = episodeItemHeightInPx * episodesSize + offset

    //If there's no space to fill, returns 0
    if (episodesTotalHeightInPx >= listHeight) return 0f

    val spaceAlreadyOccupied = episodesTotalHeightInPx / listHeight
    return 1 - spaceAlreadyOccupied
}

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


const val EPISODE_ITEM_SIZE = 80
val FIRST_EPISODE_ITEM_OFFSET_DP = 10.dp
val EPISODE_ITEM_SIZE_PLUS_SPACER_DP = (EPISODE_ITEM_SIZE).dp + 20.dp

// Forced to do this because the NestedScroll on Compose doesn't work as it should
private fun LazyListScope.LazyEpisodesList(episodes: List<Episode>) {
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