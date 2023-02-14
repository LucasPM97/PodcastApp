package com.example.podcast_player_ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.core_ui.theme.DarkGray
import com.example.podcast_player_ui.models.PlayerSize
import kotlinx.coroutines.launch

@Composable
fun PlayerDraggableBox(
    playerSize: PlayerSize,
    modifier: Modifier = Modifier,
    screenHeight: Dp,
    onComponentSizeChanged: (PlayerSize) -> Unit = {},
    content: @Composable BoxScope.(screenFilled: Float) -> Unit = {}
) {

    val coroutineScope = rememberCoroutineScope()

    var onDragging by remember { mutableStateOf(false) }
    var offsetY by remember { mutableStateOf(0f) }
    var boxHeightInPx by remember {
        mutableStateOf(0)
    }
    val dynamicBoxHeightInDp = LocalDensity.current.run {
        boxHeightInPx.toDp() - offsetY.toDp()
    }

    var nextComponentSize by remember {
        mutableStateOf(playerSize)
    }


    fun calculateHeight(playerSize: PlayerSize): Dp {
        val height = if (onDragging) {
            dynamicBoxHeightInDp
        } else {
            when (playerSize) {
                PlayerSize.None -> 0.dp
                PlayerSize.Small -> ROW_PLAYER_HEIGHT
                PlayerSize.FullScreen -> screenHeight
            }
        }
        return height
    }

    val transition = updateTransition(
        targetState = playerSize,
        label = "component size"
    )
    val animatedHeight by transition.animateDp(
        targetValueByState = {
            calculateHeight(it)
        },
        transitionSpec = { tween(durationMillis = 500) },
        label = "animate height"
    )

    val percentageOfScreenFilled = (animatedHeight * 100) / screenHeight
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
        Box(
            modifier = modifier
                .height(animatedHeight)
                .onSizeChanged {
                    if (!onDragging) {
                        boxHeightInPx = it.height
                    }
                }
                .draggable(
                    orientation = Orientation.Vertical,
                    state = rememberDraggableState { delta ->
                        coroutineScope.launch {
                            nextComponentSize = calculateNextComponentSize(
                                currentPlayerSize = playerSize,
                                boxHeight = dynamicBoxHeightInDp,
                                screenHeight = screenHeight,
                                oldOffset = offsetY,
                                newOffset = offsetY + delta,
                            )
                            offsetY += delta
                        }
                    },
                    onDragStarted = {
                        onDragging = true
                        offsetY = 0f
                    },
                    onDragStopped = {
                        onDragging = false
                        onComponentSizeChanged(nextComponentSize)
                    }
                )
        ) {
            content(percentageOfScreenFilled)
        }
    }
}

private fun calculateNextComponentSize(
    currentPlayerSize: PlayerSize,
    boxHeight: Dp,
    screenHeight: Dp,
    oldOffset: Float,
    newOffset: Float,
): PlayerSize {

    if (newOffset - oldOffset > 10 || newOffset - oldOffset < -10) {
        return if (newOffset > oldOffset) {
            when (currentPlayerSize) {
                PlayerSize.FullScreen -> PlayerSize.Small
                else -> PlayerSize.None
            }
        } else {
            when (currentPlayerSize) {
                PlayerSize.Small -> PlayerSize.FullScreen
                else -> currentPlayerSize
            }
        }
    } else {
        val screenFilled = (boxHeight * 100) / screenHeight
        return if (boxHeight < ROW_PLAYER_HEIGHT) {
            PlayerSize.None
        } else if (screenFilled > 50) {
            PlayerSize.FullScreen
        } else {
            PlayerSize.Small
        }
    }
}