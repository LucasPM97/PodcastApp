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
import com.example.podcast_player_ui.components.fullScreenPlayer.PlayerController
import com.example.podcast_player_ui.components.fullScreenPlayer.TitleWithFav

@Composable
fun FullScreenPlayer(
    episode: Episode?,
    mediaControllerState: PlayerState,
    modifier: Modifier = Modifier,
    collapsePlayer: () -> Unit = {},
    onPlayPause: (isPlaying: Boolean) -> Unit = {},
    onChangePosition: (secondsToMove: Int) -> Unit = {},
    onScrubMove: (newPosition: Long) -> Unit = {},
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
                .padding(horizontal = 30.dp)
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
            PlayerController(
                positionInSeconds = mediaControllerState.currentPositionInSeconds,
                episodeDuration = episode?.duration ?: 0,
                onScrubMove = onScrubMove,
                onPlayPause = onPlayPause,
            ) {
                PlayerButtons(
                    isPlaying = mediaControllerState.isPlaying,
                    modifier = Modifier.fillMaxWidth(),
                    onPlayClicked = onPlayPause,
                    onChangePosition = onChangePosition
                )
            }

        }
    }
}

@Composable
@Preview
private fun Preview_FullScreenPlayer() {
    PodcastAppTheme {
        FullScreenPlayer(
            episode = mockEpisode(),
            mediaControllerState = PlayerState(
                isPlaying = false,
                state = PlayerStates.Idle,
                currentPositionInSeconds = 0
            ),
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
        )
    }
}