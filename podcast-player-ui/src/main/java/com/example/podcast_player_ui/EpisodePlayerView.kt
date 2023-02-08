package com.example.podcast_player_ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.mocks.mockEpisode
import com.example.core.models.Episode
import com.example.core_ui.components.AnimatedFade
import com.example.core_ui.theme.DarkGray
import com.example.core_ui.theme.PodcastAppTheme
import com.example.podcast_player_ui.components.FullScreenPlayer
import com.example.podcast_player_ui.components.PlayerDraggableBox
import com.example.podcast_player_ui.components.RowPlayer
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

    var percentageOfScreenFilled by remember {
        mutableStateOf(0)
    }


    val backgroundColor by animateColorAsState(
        targetValue = when {
            percentageOfScreenFilled < 20f -> DarkGray
            percentageOfScreenFilled < 40f -> Color(0xFF1D2125)
            percentageOfScreenFilled < 60f -> Color(0xFF191D20)
            percentageOfScreenFilled < 85f -> Color(0xFF15181A)
            else -> MaterialTheme.colorScheme.primary
        },
        animationSpec = tween(durationMillis = 150)
    )

    Surface(
        color = backgroundColor
    ) {
        PlayerDraggableBox(
            componentSize = componentSize,
            onComponentSizeChanged = onSizeChanged,
            onHeightChanged = {
                percentageOfScreenFilled = it
            }
        ) {
            Box {
                println(percentageOfScreenFilled)
                if (percentageOfScreenFilled > 30){
                    AnimatedFade(isVisible = percentageOfScreenFilled > 50) {
                        FullScreenPlayer(
                            episode,
                            modifier = Modifier
                                .fillMaxHeight(),
                            collapsePlayer = {
                                onSizeChanged(ComponentSize.Small)
                            }
                        )
                    }
                }
                if (percentageOfScreenFilled < 50) {
                    AnimatedFade(isVisible = percentageOfScreenFilled < 30) {
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
                }
            }
        }
    }
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
