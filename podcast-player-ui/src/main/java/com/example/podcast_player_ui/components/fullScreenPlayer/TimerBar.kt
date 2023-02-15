package com.example.podcast_player_ui.components.fullScreenPlayer

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.ui.DefaultTimeBar
import androidx.media3.ui.TimeBar
import com.example.core_ui.theme.Gray
import com.example.core_ui.theme.Green

@Composable
fun TimerBar(
    durationInMilliseconds: Long,
    currentPosition: Long,
    modifier: Modifier = Modifier,
    onScrubStart: () -> Unit = {},
    onScrubMove: (position: Long) -> Unit = {},
    onScrubStop: () -> Unit = {},
    init: (DefaultTimeBar) -> Unit = {},
) {
    AndroidView(
        factory = { context ->
            DefaultTimeBar(context).apply {
                setPlayedColor(Green.toArgb())
                setScrubberColor(Green.toArgb())
                setScrubberColor(Gray.toArgb())
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
            it.setDuration(durationInMilliseconds)
            it.setPosition(currentPosition)
        },
        modifier = modifier
    )
}