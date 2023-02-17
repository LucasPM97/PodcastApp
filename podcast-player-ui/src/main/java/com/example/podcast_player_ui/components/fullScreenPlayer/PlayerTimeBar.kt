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
import com.example.core_ui.theme.PodcastAppTheme
import kotlin.time.DurationUnit

@androidx.media3.common.util.UnstableApi
@Composable
fun PlayerTimeBar(
    currentPosition: Long,
    bufferedPosition: Long,
    episodeDuration: Int,
    modifier: Modifier = Modifier,
    onPlayPause: (isPlaying: Boolean) -> Unit = {},
    onScrubMove: (newPosition: Long) -> Unit = {},
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
            duration = episodeDuration * 1000L,
            currentPosition = currentPosition,
            bufferedPosition = bufferedPosition
        )
        SpacerVertical(space = 10)
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = currentPosition.toPlayerDurationText(DurationUnit.MILLISECONDS),
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = episodeDuration.toPlayerDurationText(DurationUnit.SECONDS),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview
@Composable
private fun PreviewPlayerTimeBar() {
    PodcastAppTheme {
        var position by remember {
            mutableStateOf(0L)
        }
        PlayerTimeBar(
            currentPosition = position,
            bufferedPosition = position,
            episodeDuration = 4000,
            onScrubMove = {
                position = it
            },
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}