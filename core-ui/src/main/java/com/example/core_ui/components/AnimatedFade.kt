package com.example.core_ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer

@Composable
fun AnimatedFade(
    alpha: Float,
    content: @Composable() BoxScope.() -> Unit
) {
    val animatedAlpha: Float by animateFloatAsState(alpha)
    Box(
        Modifier
            .graphicsLayer(alpha = animatedAlpha)
    ) {
        content()
    }
}