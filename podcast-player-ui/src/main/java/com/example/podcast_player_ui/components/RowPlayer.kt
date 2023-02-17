package com.example.podcast_player_ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.setPadding
import com.example.core.mocks.mockEpisode
import com.example.core.models.Episode
import com.example.core_ui.components.AppAsyncImage
import com.example.core_ui.components.SpacerHorizontal20
import com.example.core_ui.components.SpacerVertical
import com.example.core_ui.extensions.roundedRectangle
import com.example.core_ui.theme.PodcastAppTheme
import com.example.podcast_player_ui.components.fullScreenPlayer.TimerBar
import com.example.podcast_player_ui.components.rowPlayer.EpisodeTitle
import com.example.podcast_player_ui.components.rowPlayer.PlayerButtons

// Vertical padding + component size
val ROW_PLAYER_HEIGHT = 120.dp

@Composable
fun RowPlayer(
    episode: Episode?,
    mediaControllerState: PlayerState,
    modifier: Modifier = Modifier,
    expandPlayer: () -> Unit = {},
    onPlayPause: (isPlaying: Boolean) -> Unit = {},
    onChangePosition: (secondsToMove: Int) -> Unit = {}
) {
    Column(modifier) {
        TimerBar(
            duration = (episode?.duration ?: 0) * 1000L,
            currentPosition = mediaControllerState.currentPosition,
            init = {
                it.hideScrubber(true)
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Red)
                .height(2.dp)
                .clipToBounds()
        )
        SpacerVertical(space = 5)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .clickable {
                    expandPlayer()
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AppAsyncImage(
                    modifier = Modifier
                        .size(70.dp)
                        .roundedRectangle(10.dp),
                    imageUrl = episode?.imageUrl ?: "",
                    contentDescription = episode?.name ?: "",
                )
                SpacerHorizontal20()
                EpisodeTitle(episode)
            }
            PlayerButtons(
                isPlaying = mediaControllerState.isPlaying,
                onPlayClicked = onPlayPause,
                onChangePosition = onChangePosition
            )
        }
    }
}


@Preview
@Composable
fun Preview_RowPlayer() {
    PodcastAppTheme {
        val episode = mockEpisode().copy(
            duration = 4000
        )
        RowPlayer(
            episode = episode,
            mediaControllerState = PlayerState(
                isPlaying = true,
                state = PlayerStates.Idle,
                currentPosition = 2000,
                bufferedPosition = 3000,
            ),
        )
    }
}