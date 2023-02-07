package com.example.podcast_player_ui.components

import androidx.activity.compose.BackHandler
import androidx.compose.animation.*
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.mocks.mockEpisode
import com.example.core.models.Episode
import com.example.core_ui.theme.DarkGray
import com.example.core_ui.theme.Green
import com.example.core_ui.theme.PodcastAppTheme
import com.example.podcast_player_ui.PlayerViewModel
import com.example.podcast_player_ui.extensions.componentSizeHeight
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

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun Content(
    componentSize: ComponentSize,
    episode: Episode?,
    onSizeChanged: (ComponentSize) -> Unit = {}
) {

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    BackHandler(componentSize == ComponentSize.FullScreen) {
        onSizeChanged(ComponentSize.Small)
    }

    var onDragging by remember { mutableStateOf(false) }
    var offsetY by remember { mutableStateOf(0f) }
    val offsetYInDp =
        LocalDensity.current.run { offsetY.toDp() }

    var componentCurrentHeighInPx by remember {
        mutableStateOf(0f)
    }
    val componentCurrentHeighBeforeDragInDp =
        LocalDensity.current.run {
            componentCurrentHeighInPx.toDp()
        }
    val componentDynamicHeighInDp =
        LocalDensity.current.run {
            componentCurrentHeighInPx.toDp() - offsetYInDp
        }

    println("OffsetY: $offsetYInDp")
    println("Component Height: $componentDynamicHeighInDp")

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(0.dp, screenHeight)
            .background(DarkGray)
            .componentSizeHeight(
                componentSize,
                screenHeight,
                dynamicHeight = if (onDragging) componentDynamicHeighInDp
                else 0.dp
            )
            .onSizeChanged {
                if (!onDragging) {
                    componentCurrentHeighInPx = it.height.toFloat()
                }
            }
            .draggable(
                orientation = Orientation.Vertical,
                state = rememberDraggableState { delta ->
                    offsetY += delta
                },
                onDragStarted = {
                    onDragging = true
                },
                onDragStopped = {
                    var nextComponentSize = componentSize
                    if (componentCurrentHeighBeforeDragInDp > componentDynamicHeighInDp) {
                        when (componentSize) {
                            ComponentSize.None -> {}
                            ComponentSize.Small -> nextComponentSize = ComponentSize.None
                            ComponentSize.FullScreen -> nextComponentSize = ComponentSize.Small
                        }
                    } else {
                        when (componentSize) {
                            ComponentSize.Small -> nextComponentSize = ComponentSize.FullScreen
                            else -> {}
                        }
                    }

                    onSizeChanged(nextComponentSize)
                    onDragging = false
                    offsetY = 0f
                }
            )
            .animateContentSize()
    ) {
        when (componentSize) {
            ComponentSize.None -> {}
            ComponentSize.Small -> RowPlayer(episode,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onSizeChanged(ComponentSize.FullScreen)
                    }
                    .padding(bottom = 20.dp, top = 10.dp)
                    .padding(horizontal = 10.dp))
            ComponentSize.FullScreen -> {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(Green)
                )
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
