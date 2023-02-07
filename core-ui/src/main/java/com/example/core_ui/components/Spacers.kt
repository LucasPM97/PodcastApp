package com.example.core_ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SpacerVertical20() {
    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
fun SpacerHorizontal20() {
    Spacer(modifier = Modifier.width(20.dp))
}