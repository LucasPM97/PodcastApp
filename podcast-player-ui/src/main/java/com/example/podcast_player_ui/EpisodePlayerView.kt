package com.example.podcast_player_ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.media3.common.MediaItem
import com.example.core.mocks.mockEpisode
import com.example.core.models.Episode
import com.example.core_ui.theme.PodcastAppTheme
import com.example.podcast_player_ui.components.*
import com.example.podcast_player_ui.models.PlayerSize

@Composable
fun EpisodePlayerView(
    episode: Episode?,
    clearPlaylist: () -> Unit = {}
) {

    Content(
        episode,
        componentClosed = {
            clearPlaylist()
        }
    )
}

@Composable
private fun Content(
    episode: Episode?,
    componentClosed: () -> Unit = {}
) {

    var playerSize by remember {
        mutableStateOf(PlayerSize.None)
    }

    fun onSizeChanged(newPlayerSize: PlayerSize) {
        playerSize = newPlayerSize
    }

    val isFullScreen = playerSize == PlayerSize.FullScreen
    BackHandler(isFullScreen) {
        onSizeChanged(PlayerSize.Small)
    }

    val mediaController by rememberMediaController()
    val mediaControllerState by rememberMediaControllerState(mediaController)
    LaunchedEffect(mediaController, episode) {
        if (episode == null) mediaController?.release()

        episode?.audioUrl?.let { audioUrl ->
            onSizeChanged(PlayerSize.FullScreen)

            val media = MediaItem.Builder()
                .setMediaId(audioUrl)
                .build()
            mediaController?.setMediaItem(media)
            mediaController?.prepare()
            mediaController?.playWhenReady = true
        }
    }
    fun closePlayer() {
        if (playerSize == PlayerSize.None) {
            mediaController?.stop()
            mediaController?.release()
            componentClosed()
        }
    }

    fun handlePlayButtonClicked(isPlaying: Boolean) {
        if (isPlaying) {
            mediaController?.play()
        } else {
            mediaController?.pause()
        }
    }

    fun handleOnScrubMove(newPosition: Long) {
        val position = if (newPosition < 0) 0L else newPosition
        mediaController?.seekTo(position)
    }

    fun handleChangePosition(secondsToMove: Int) {
        val newPosition =
            mediaControllerState.currentPosition + secondsToMove * 1000L
        mediaController?.seekTo(
            if (newPosition < 0) 0 else newPosition
        )
    }

    DraggablePlayerBoxWithAnimatedContent(
        playerSize = playerSize,
        onSizeChanged = {
            onSizeChanged(it)
        },
        onPlayerClose = {
            closePlayer()
        },
        fullScreenPlayer = {
            FullScreenPlayer(
                episode,
                mediaControllerState,
                modifier = Modifier
                    .fillMaxHeight(),
                collapsePlayer = {
                    onSizeChanged(PlayerSize.Small)
                },
                onPlayPause = {
                    handlePlayButtonClicked(it)
                },
                onChangePosition = {
                    handleChangePosition(it)
                },
                onScrubMove = {
                    handleOnScrubMove(it)
                }
            )
        },
        rowPlayer = {
            RowPlayer(
                episode,
                mediaControllerState = mediaControllerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 40.dp),
                expandPlayer = {
                    onSizeChanged(PlayerSize.FullScreen)
                },
                onPlayPause = {
                    handlePlayButtonClicked(it)
                },
                onChangePosition = {
                    handleChangePosition(it)
                }
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun Preview_RowPlayer() {
    PodcastAppTheme {
        var playerSize by remember {
            mutableStateOf(PlayerSize.Small)
        }
        Scaffold(
            bottomBar = {
                Content(
                    episode = mockEpisode(),
                )
            }
        ) {
            Column(
                Modifier
                    .padding(it)
                    .fillMaxSize()
            ) {

            }
        }

    }
}
