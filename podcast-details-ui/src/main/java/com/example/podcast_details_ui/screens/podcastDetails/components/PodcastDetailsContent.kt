package com.example.podcast_details_ui.screens.podcastDetails.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core_ui.components.AppAsyncImage
import com.example.core_ui.theme.PodcastAppTheme
import com.example.podcast_details_domain.mocks.mockPodcast
import com.example.podcast_details_domain.models.PodcastDetails

@Composable
fun PodcastDetailsContent(
    podcastDetails: PodcastDetails,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(horizontal = 10.dp)
    ) {
        PodcastImage(
            imageUrl = podcastDetails.imageUrl,
            podcastName = podcastDetails.name
        )
        Spacer(modifier = Modifier.height(20.dp))
        PodcastTitle(
            podcastName = podcastDetails.name ?: "",
            authorName = podcastDetails.authorName
        )
        Description(podcastDetails.description)
        Spacer(modifier = Modifier.height(20.dp))
        ActionButtons(
            lastEpisode = podcastDetails.episodes?.last(),
            fav = false,
            onFav = {

            },
            onPlayEpisode = {

            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun PodcastImage(imageUrl: String?, podcastName: String?) {
    AppAsyncImage(
        modifier = Modifier
            .fillMaxWidth()
            .height(360.dp)
            .clip(
                RoundedCornerShape(20.dp)
            ),
        imageUrl = imageUrl ?: "",
        contentDescription = podcastName ?: ""
    )
}

@Preview
@Composable
fun Preview_PodcastDetailsContent() {
    PodcastAppTheme {
        PodcastDetailsContent(
            podcastDetails = mockPodcast,
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        )
    }
}