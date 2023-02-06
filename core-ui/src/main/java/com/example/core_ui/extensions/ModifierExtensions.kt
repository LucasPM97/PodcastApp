package com.example.core_ui.extensions

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp

fun Modifier.roundedRectangle(radius: Dp) = composed { clip(
    RoundedCornerShape(radius)
) }