package com.example.core_ui.extensions

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp

@Composable
fun Modifier.roundedRectangle(radius: Dp) = clip(
    RoundedCornerShape(radius)
)