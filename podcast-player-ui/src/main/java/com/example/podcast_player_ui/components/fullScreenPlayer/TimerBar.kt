package com.example.podcast_player_ui.components.fullScreenPlayer

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.ui.DefaultTimeBar
import androidx.media3.ui.TimeBar
import com.example.core_ui.theme.PlayerTimeBarBufferedColor
import com.example.core_ui.theme.PlayerTimeBarPrimaryColor
import com.example.core_ui.theme.PlayerTimeBarUnplayedColor
import com.example.core_ui.theme.PodcastAppTheme

@Composable
fun TimerBar(
    duration: Long,
    currentPosition: Long,
    modifier: Modifier = Modifier,
    bufferedPosition: Long = 0,
    onScrubStart: () -> Unit = {},
    onScrubMove: (position: Long) -> Unit = {},
    onScrubStop: () -> Unit = {},
    init: (DefaultTimeBar) -> Unit = {}
) {
    AndroidView(
        factory = { context ->
            DefaultTimeBar(context).apply {
                setPlayedColor(PlayerTimeBarPrimaryColor.toArgb())
                setScrubberColor(PlayerTimeBarPrimaryColor.toArgb())
                setBufferedColor(PlayerTimeBarBufferedColor.toArgb())
                setUnplayedColor(PlayerTimeBarUnplayedColor.toArgb())
                addListener(
                    object : TimeBar.OnScrubListener {
                        override fun onScrubStart(timeBar: TimeBar, position: Long) {
                            onScrubStart()
                        }

                        override fun onScrubMove(timeBar: TimeBar, position: Long) {
                            onScrubMove(position)
                        }

                        override fun onScrubStop(
                            timeBar: TimeBar,
                            position: Long,
                            canceled: Boolean
                        ) {
                            onScrubStop()
                        }

                    }
                )
                init(this)
            }
        },
        update = {
            it.setDuration(duration)
            it.setBufferedPosition(bufferedPosition)
            it.setPosition(currentPosition)
        },
        modifier = modifier
    )
}

@Preview(showBackground = true, backgroundColor = 0xFF111315)
@Composable
fun Preview_TimeBar() {
    PodcastAppTheme {
        TimerBar(
            duration = 4000L,
            currentPosition = 1000L,
            bufferedPosition = 2000L,
        )
    }
}