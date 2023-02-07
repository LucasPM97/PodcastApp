package com.example.podcast_details_ui.screens.podcastDetails.components

import android.graphics.drawable.Drawable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core_ui.components.SpacerVertical20
import com.example.core_ui.components.fillLazyColumn
import com.example.core_ui.theme.PodcastAppTheme
import com.example.core.mocks.mockPodcast
import com.example.core.models.Episode
import com.example.core.models.podcastDetails.PodcastDetails


@Composable
fun PodcastDetailsContent(
    podcastDetails: PodcastDetails,
    modifier: Modifier = Modifier,
    scrollState: LazyListState = rememberLazyListState(),
    imageLoaded: (Drawable) -> Unit = {},
    openPodcastPlayer: (episodeUuid: String) -> Unit = {}
) {

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
            SpacerVertical20()
            PodcastImage(
                imageUrl = podcastDetails.imageUrl,
                podcastName = podcastDetails.name,
                imageLoaded = imageLoaded
            )
            SpacerVertical20()
            PodcastTitle(
                podcastName = podcastDetails.name ?: "",
                authorName = podcastDetails.authorName
            )
        }
        item {
            Description(podcastDetails.description)
            SpacerVertical20()
            ActionButtons(
                lastEpisode = podcastDetails.episodes?.last(),
                fav = false,
                onFav = {

                },
                onPlayEpisode = {
                    openPodcastPlayer(
                        podcastDetails.episodes?.firstOrNull()?.uuid ?: ""
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )
            SpacerVertical20()
            podcastDetails.episodes?.let { _ ->
                ScrollToEpisodesButton(
                    modifier = Modifier.fillMaxWidth(),
                    scrollState = scrollState,
                    firstEpisodeItemIndex = 2
                )
                SpacerVertical20()
            }
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
        SpacerVertical20()
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