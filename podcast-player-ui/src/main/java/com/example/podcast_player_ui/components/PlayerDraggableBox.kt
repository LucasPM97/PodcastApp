package com.example.podcast_player_ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.podcast_player_ui.extensions.componentSizeHeight
import com.example.podcast_player_ui.models.ComponentSize
import kotlinx.coroutines.launch

@Composable
fun PlayerDraggableBox(
    componentSize: ComponentSize,
    modifier: Modifier = Modifier,
    screenHeight: Dp,
    onComponentSizeChanged: (ComponentSize) -> Unit = {},
    onHeightChanged: (boxHeightInPx: Int) -> Unit = {},
    content: @Composable BoxScope.() -> Unit = {}
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

    Box(
        modifier = modifier
            .componentSizeHeight(
                componentSize,
                dynamicHeight = if (onDragging) dynamicBoxHeightInDp
                else 0.dp
            )
            .onSizeChanged {
                if (!onDragging) {
                    boxHeightInPx = it.height
                }
                onHeightChanged(it.height)
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
            .animateContentSize()
    ) {
        content()
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