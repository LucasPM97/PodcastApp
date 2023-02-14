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
import com.example.podcast_player_ui.models.ComponentSize
import kotlinx.coroutines.launch

@Composable
fun PlayerDraggableBox(
    componentSize: ComponentSize,
    modifier: Modifier = Modifier,
    screenHeight: Dp,
    onComponentSizeChanged: (ComponentSize) -> Unit = {},
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
        mutableStateOf(componentSize)
    }


    fun calculateHeight(componentSize: ComponentSize): Dp {
        val height = if (onDragging) {
            dynamicBoxHeightInDp
        } else {
            when (componentSize) {
                ComponentSize.None -> 0.dp
                ComponentSize.Small -> ROW_PLAYER_HEIGHT
                ComponentSize.FullScreen -> screenHeight
            }
        }
        return height
    }

    val transition = updateTransition(
        targetState = componentSize,
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
                                currentComponentSize = componentSize,
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
    currentComponentSize: ComponentSize,
    boxHeight: Dp,
    screenHeight: Dp,
    oldOffset: Float,
    newOffset: Float,
): ComponentSize {

    if (newOffset - oldOffset > 10 || newOffset - oldOffset < -10) {
        return if (newOffset > oldOffset) {
            when (currentComponentSize) {
                ComponentSize.FullScreen -> ComponentSize.Small
                else -> ComponentSize.None
            }
        } else {
            when (currentComponentSize) {
                ComponentSize.Small -> ComponentSize.FullScreen
                else -> currentComponentSize
            }
        }
    } else {
        val screenFilled = (boxHeight * 100) / screenHeight
        return if (boxHeight < ROW_PLAYER_HEIGHT) {
            ComponentSize.None
        } else if (screenFilled > 50) {
            ComponentSize.FullScreen
        } else {
            ComponentSize.Small
        }
    }
}