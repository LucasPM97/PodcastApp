package com.example.podcast_player_ui.components.fullScreenPlayer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.media3.session.MediaController
import com.example.core.extensions.durationText
import com.example.core.extensions.toPlayerDurationText
import com.example.core.mocks.mockEpisode
import com.example.core.models.Episode
import com.example.core_ui.components.SpacerVertical
import com.example.core_ui.theme.PodcastAppTheme
import kotlinx.coroutines.delay
import kotlin.time.DurationUnit

@androidx.media3.common.util.UnstableApi
@Composable
fun PlayerTimeBar(
    mediaController: MediaController?,
    isPlaying: Boolean,
    episode: Episode?,
    modifier: Modifier = Modifier,
) {

    var timerInSeconds by remember { mutableStateOf(0) }
    LaunchedEffect(isPlaying, episode) {
        episode?.let {
            if (isPlaying) {
                val episodeDuration = episode.duration ?: 0
                while (episodeDuration > timerInSeconds) {
                    delay(1000)
                    timerInSeconds++
                }
            }
        }
    }

    Column(modifier) {
        TimerBar(
            onScrubStart = {
                mediaController?.pause()
            },
            onScrubMove = { position ->
                timerInSeconds = (position / 1000).toInt()
                mediaController?.seekTo(position)
            },
            onScrubStop = {
                mediaController?.play()
            },
            durationInMilliseconds = (episode?.duration ?: 0) * 1000L,
            currentPosition = timerInSeconds * 1000L,
        )
        SpacerVertical(space = 10)
        Row(
            Modifier.fillMaxWidth().padding(horizontal = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = timerInSeconds.toPlayerDurationText(DurationUnit.SECONDS),
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = episode?.durationText() ?: "",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview
@Composable
private fun PreviewPlayerTimeBar() {
    PodcastAppTheme {
        PlayerTimeBar(
            mediaController = null,
            isPlaying = false,
            episode = mockEpisode(),
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}