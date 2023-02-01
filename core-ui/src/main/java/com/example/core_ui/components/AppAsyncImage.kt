package com.example.core_ui.components

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.SubcomposeAsyncImage
import com.example.core_ui.theme.Gray

@Composable
fun AppAsyncImage(
    imageUrl: String,
    contentDescription: String,
    modifier: Modifier = Modifier
) {
    SubcomposeAsyncImage(
        model = imageUrl,
        modifier = modifier
            .background(Gray),
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop

    )
}