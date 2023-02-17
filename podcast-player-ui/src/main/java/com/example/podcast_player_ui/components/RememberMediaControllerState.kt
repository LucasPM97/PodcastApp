package com.example.podcast_player_ui.components

import androidx.compose.runtime.*
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.Timeline
import kotlinx.coroutines.delay

@Composable
fun rememberMediaControllerState(player: Player?): MutableState<PlayerState> {
    val mediaControllerState = remember {
        mutableStateOf(
            PlayerState(
                isPlaying = false,
                state = PlayerStates.Idle,
                currentPosition = 0,
                bufferedPosition = 0
            )
        )
    }

    val isPlaying = mediaControllerState.value.isPlaying
    val state = mediaControllerState.value.state

    LaunchedEffect(isPlaying, state) {
        player?.let {
            if (isPlaying) {
                while (state != PlayerStates.Ended) {
                    delay(1000)
                    mediaControllerState.value = mediaControllerState.value.copy(
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
                mediaControllerState.value = mediaControllerState.value.copy(
                    state = mapPlayerStateToEnum(playbackState)
                )
            }

            override fun onIsPlayingChanged(isPlaying: Boolean) {
                super.onIsPlayingChanged(isPlaying)
                mediaControllerState.value = mediaControllerState.value.copy(
                    isPlaying = isPlaying
                )
            }

            override fun onEvents(player: Player, events: Player.Events) {
                super.onEvents(player, events)
                mediaControllerState.value = mediaControllerState.value.copy(
                    currentPosition = player.currentPosition,
                    bufferedPosition = player.bufferedPosition
                )
            }
        }
        player?.addListener(observer)


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