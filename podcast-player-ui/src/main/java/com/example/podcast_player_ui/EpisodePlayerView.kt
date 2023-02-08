package com.example.podcast_player_ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.core.mocks.mockEpisode
import com.example.core.models.Episode
import com.example.core_ui.components.AnimatedFade
import com.example.core_ui.theme.DarkGray
import com.example.core_ui.theme.PodcastAppTheme
import com.example.podcast_player_ui.components.FullScreenPlayer
import com.example.podcast_player_ui.components.PlayerDraggableBox
import com.example.podcast_player_ui.components.ROW_PLAYER_HEIGHT
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

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    var draggableBoxHeight by remember {
        mutableStateOf(0.dp)
    }
    val percentageOfScreenFilled = (draggableBoxHeight * 100) / screenHeight

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
            modifier = Modifier
                .heightIn(0.dp, screenHeight)
                .fillMaxWidth(),
            componentSize = componentSize,
            onComponentSizeChanged = onSizeChanged,
            onHeightChanged = {
                draggableBoxHeight = it
            }
        ) {
            Box {
                if (percentageOfScreenFilled > PERCENTAGE_OF_SCREEN_WHEN_FULLSREEN_PLAYER_ISVISIBLE) {
                    val fullScreenPlayerAlpha =
                        calculateFullScreenPlayerAlpha(percentageOfScreenFilled)
                    AnimatedFade(alpha = fullScreenPlayerAlpha) {
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
                if (percentageOfScreenFilled < PERCENTAGE_OF_SCREEN_WHEN_ROW_PLAYER_ISVISIBLE) {
                    val rowPlayerAlpha =
                        calculateRowPlayerAlpha(screenHeight, percentageOfScreenFilled)

                    AnimatedFade(alpha = rowPlayerAlpha) {
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

private const val PERCENTAGE_OF_SCREEN_WHEN_FULLSREEN_PLAYER_ISVISIBLE = 40f
private fun calculateFullScreenPlayerAlpha(percentageOfScreenFilled: Float) =
    (percentageOfScreenFilled - PERCENTAGE_OF_SCREEN_WHEN_FULLSREEN_PLAYER_ISVISIBLE) / PERCENTAGE_OF_SCREEN_WHEN_FULLSREEN_PLAYER_ISVISIBLE


private const val PERCENTAGE_OF_SCREEN_WHEN_ROW_PLAYER_ISVISIBLE = 50f
private fun calculateRowPlayerAlpha(
    screenHeight: Dp,
    percentageOfScreenFilled: Float
): Float {
    val rowPlayerHeight = ROW_PLAYER_HEIGHT
    val screenFilledPercentageByRowPlayer = (rowPlayerHeight * 100) / screenHeight
    return (PERCENTAGE_OF_SCREEN_WHEN_ROW_PLAYER_ISVISIBLE - percentageOfScreenFilled) / (PERCENTAGE_OF_SCREEN_WHEN_ROW_PLAYER_ISVISIBLE - screenFilledPercentageByRowPlayer)
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
