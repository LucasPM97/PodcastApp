package com.example.podcast_player_ui.components

import androidx.compose.runtime.*
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.Timeline
import kotlinx.coroutines.delay

@Composable
fun rememberMediaControllerState(player: Player?): PlayerState {
    var mediaControllerState by remember {
        mutableStateOf(
            PlayerState(
                isPlaying = false,
                state = PlayerStates.Idle,
                currentPosition = 0,
                bufferedPosition = 0
            )
        )
    }

    LaunchedEffect(mediaControllerState.isPlaying) {
        val isPlaying = mediaControllerState.isPlaying
        player?.let {
            if (isPlaying) {
                while (true) {
                    delay(1000)
                    mediaControllerState = mediaControllerState.copy(
                        currentPosition = player.currentPosition,
                        bufferedPosition = player.bufferedPosition

                    )
                }
            }
        }
    }

    DisposableEffect(player) {

        val observer = object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                super.onPlaybackStateChanged(playbackState)
                mediaControllerState = mediaControllerState.copy(
                    state = mapPlayerStateToEnum(playbackState)
                )
            }

            override fun onIsPlayingChanged(isPlaying: Boolean) {
                super.onIsPlayingChanged(isPlaying)
                mediaControllerState = mediaControllerState.copy(
                    isPlaying = isPlaying
                )
            }

            override fun onEvents(player: Player, events: Player.Events) {
                super.onEvents(player, events)
                mediaControllerState = mediaControllerState.copy(
                    currentPosition = player.currentPosition,
                    bufferedPosition = player.bufferedPosition
                )
            }
        }
        //Init player state
        player?.let {
            it.addListener(observer)
            mediaControllerState = mediaControllerState.copy(
                isPlaying = it.isPlaying,
                state = mapPlayerStateToEnum(it.playbackState),
                currentPosition = player.currentPosition,
                bufferedPosition = player.bufferedPosition
            )
        }

        onDispose {
            player?.removeListener(observer)
        }
    }

    return mediaControllerState
}

data class PlayerState(
    val state: PlayerStates,
    val isPlaying: Boolean,
    val currentPosition: Long,
    val bufferedPosition: Long,
)

private fun mapPlayerStateToEnum(state: Int): PlayerStates {
    return when (state) {
        Player.STATE_IDLE -> PlayerStates.Idle
        Player.STATE_BUFFERING -> PlayerStates.Buffering
        Player.STATE_ENDED -> PlayerStates.Ended
        Player.STATE_READY -> PlayerStates.Ready
        else -> throw Exception("Invalid Player state")
    }
}

enum class PlayerStates {
    Idle,
    Buffering,
    Ready,
    Ended
}