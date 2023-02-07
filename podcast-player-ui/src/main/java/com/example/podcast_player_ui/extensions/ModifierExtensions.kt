package com.example.podcast_player_ui.extensions

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.podcast_player_ui.models.ComponentSize

@Composable
fun Modifier.dynamicHeight(componentSize: ComponentSize, smallHeight: Dp) = when (componentSize) {
    ComponentSize.None -> height(0.dp)
    ComponentSize.Small -> this
    ComponentSize.FullScreen -> fillMaxHeight()
}