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
import com.example.podcast_player_ui.models.ComponentSize
import org.koin.androidx.compose.getViewModel

@Composable
fun EpisodePlayerView(
    defaultSize: ComponentSize,
    viewModel: PlayerViewModel = getViewModel(),
    onSizeChanged: (ComponentSize) -> Unit = {}
) {

    val episode by viewModel.episode.collectAsState(initial = null)

    Content(
        defaultSize,
        episode,
        onSizeChanged
    )
}

@Composable
private fun Content(
    componentSize: ComponentSize,
    episode: Episode?,
    onSizeChanged: (ComponentSize) -> Unit = {}
) {

    val isFullScreen = componentSize == ComponentSize.FullScreen
    BackHandler(isFullScreen) {
        onSizeChanged(ComponentSize.Small)
    }

    val mediaController by rememberMediaController()
    val mediaControllerState by rememberMediaControllerState(mediaController)
    LaunchedEffect(mediaController) {
//        episode?.audioUrl?.let { audioUrl ->
//            mediaController?.addMediaItem(
//                MediaItem.fromUri(audioUrl)
//            )
//        }
        mediaController?.prepare()
    }

    AnimatedPlayerBoxContent(
        componentSize = componentSize,
        onSizeChanged = onSizeChanged,
        fullScreenPlayer = {
            FullScreenPlayer(
                episode,
                mediaControllerState,
                modifier = Modifier
                    .fillMaxHeight(),
                collapsePlayer = {
                    onSizeChanged(ComponentSize.Small)
                },
                onPlayClicked = {
                    if (mediaControllerState.isPlaying) {
                        mediaController?.pause()
                    } else {
                        mediaController?.play()
                    }
                }
            )
        },
        rowPlayer = {
            RowPlayer(
                episode,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp, top = 10.dp)
                    .padding(horizontal = 10.dp),
                expandePlayer = {
                    onSizeChanged(ComponentSize.FullScreen)
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
            mutableStateOf(ComponentSize.Small)
        }
        Scaffold(
            bottomBar = {
                Content(
                    episode = mockEpisode(),
                    componentSize = playerSize,
                    onSizeChanged = {
                        playerSize = it
                    }
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
