package com.example.podcast_player_ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.podcast_player_ui.extensions.componentSizeHeight
import com.example.podcast_player_ui.models.ComponentSize
import kotlin.math.roundToInt

@Composable
fun PlayerDraggableBox(
    componentSize: ComponentSize,
    modifier: Modifier = Modifier,
    onComponentSizeChanged: (ComponentSize) -> Unit = {},
    onHeightChanged: (screenFilledPercentage: Int) -> Unit = {},
    content: @Composable BoxScope.() -> Unit = {}
) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

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

    LaunchedEffect(componentDynamicHeighInDp) {
        val percentageOfScreenFilled = (componentDynamicHeighInDp * 100) / screenHeight

        onHeightChanged(percentageOfScreenFilled.roundToInt())
    }

    Box(
        modifier = modifier
            .heightIn(0.dp, screenHeight)
            .fillMaxWidth()
            .componentSizeHeight(
                componentSize,
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
                    offsetY = 0f
                },
                onDragStopped = {
                    val nextComponentSize = calculateNextComponentSize(
                        componentSize,
                        componentCurrentHeighBeforeDragInDp,
                        componentDynamicHeighInDp
                    )
                    onComponentSizeChanged(nextComponentSize)
                    onDragging = false
                }
            )
            .animateContentSize()
    ) {
        content()
    }
}

private fun calculateNextComponentSize(
    componentSize: ComponentSize,
    componentCurrentHeighBeforeDragInDp: Dp,
    componentDynamicHeighInDp: Dp
): ComponentSize {
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
    return nextComponentSize
}