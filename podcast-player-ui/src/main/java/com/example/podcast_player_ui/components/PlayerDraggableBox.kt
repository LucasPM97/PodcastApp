package com.example.podcast_player_ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
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
import androidx.compose.ui.unit.dp
import com.example.core_ui.theme.DarkGray
import com.example.podcast_player_ui.extensions.componentSizeHeight
import com.example.podcast_player_ui.models.ComponentSize

@Composable
fun PlayerDraggableBox(
    componentSize: ComponentSize,
    modifier: Modifier = Modifier,
    onSizeChanged: (ComponentSize) -> Unit = {},
    content: @Composable BoxScope.() -> Unit = {}
) {
    // Max height
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


    Box(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(0.dp, screenHeight)
            .background(DarkGray)
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
        content()
    }
}