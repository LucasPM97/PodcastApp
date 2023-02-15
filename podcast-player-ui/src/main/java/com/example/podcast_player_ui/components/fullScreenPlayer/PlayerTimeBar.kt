package com.example.podcast_player_ui.components.fullScreenPlayer

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.extensions.toPlayerDurationText
import com.example.core_ui.components.SpacerVertical
import com.example.core_ui.components.SpacerVertical20
import com.example.core_ui.theme.PodcastAppTheme
import kotlin.time.DurationUnit

@androidx.media3.common.util.UnstableApi
@Composable
fun PlayerController(
    positionInSeconds: Int,
    episodeDuration: Int,
    modifier: Modifier = Modifier,
    onPlayPause: (isPlaying: Boolean) -> Unit = {},
    onScrubMove: (newPosition: Long) -> Unit = {},
    playerActionButtons: @Composable ColumnScope.(positionInSeconds: Int) -> Unit = {},
) {

    Column(modifier) {
        TimerBar(
            onScrubStart = {
                onPlayPause(false)
            },
            onScrubMove = onScrubMove,
            onScrubStop = {
                onPlayPause(true)
            },
            durationInMilliseconds = episodeDuration * 1000L,
            currentPosition = positionInSeconds * 1000L,
        )
        SpacerVertical(space = 10)
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = positionInSeconds.toPlayerDurationText(DurationUnit.SECONDS),
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = episodeDuration.toPlayerDurationText(DurationUnit.SECONDS),
                style = MaterialTheme.typography.bodySmall
            )
        }
        SpacerVertical20()
        playerActionButtons(positionInSeconds)
    }
}

@Preview
@Composable
private fun PreviewPlayerTimeBar() {
    PodcastAppTheme {
        var positionInSeconds by remember {
            mutableStateOf(0)
        }
        PlayerController(
            positionInSeconds = positionInSeconds,
            episodeDuration = 4000,
            onScrubMove = {
                positionInSeconds = (it / 1000).toInt()
            },
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}