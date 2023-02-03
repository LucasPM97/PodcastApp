package com.example.core_ui.components

import android.graphics.drawable.Drawable
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.core_ui.theme.Gray

@Composable
fun AppAsyncImage(
    imageUrl: String,
    contentDescription: String,
    modifier: Modifier = Modifier,
    imageLoaded: (image: Drawable) -> Unit = {}
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        modifier = modifier
            .background(Gray),
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop,
        onSuccess = {
            val drawable = it.result.drawable
            imageLoaded(drawable)
        }
    )
}