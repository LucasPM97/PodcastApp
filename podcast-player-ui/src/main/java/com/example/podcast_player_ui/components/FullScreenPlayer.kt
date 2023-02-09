package com.example.podcast_player_ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.mocks.mockEpisode
import com.example.core.models.Episode
import com.example.core_ui.components.AppAsyncImage
import com.example.core_ui.components.SpacerVertical20
import com.example.core_ui.extensions.roundedRectangle
import com.example.core_ui.theme.PodcastAppTheme
import com.example.podcast_player_ui.components.fullScreenPlayer.CollapseButton
import com.example.podcast_player_ui.components.fullScreenPlayer.PlayerButtons
import com.example.podcast_player_ui.components.fullScreenPlayer.TitleWithFav

@Composable
fun FullScreenPlayer(
    episode: Episode?,
    modifier: Modifier = Modifier,
    collapsePlayer: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .padding(10.dp)
    ) {
        CollapseButton(collapsePlayer)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(horizontal = 50.dp)
                .weight(1f)
        ) {
            AppAsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .roundedRectangle(10.dp),
                imageUrl = episode?.imageUrl ?: "",
                contentDescription = episode?.name ?: "",
            )
            SpacerVertical20()
            TitleWithFav(episode)
            SpacerVertical20()
            SpacerVertical20()
            PlayerButtons(
                modifier = Modifier.fillMaxWidth(),
                onPlayClicked = onPlayClicked
            )
        }
    }
}

@Composable
@Preview
private fun Preview_FullScreenPlayer() {
    PodcastAppTheme {
        FullScreenPlayer(
            episode = mockEpisode(),
            mediaControllerState = null,
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
        )
    }
}