package com.example.core_ui.extensions

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.Color
import androidx.palette.graphics.Palette

fun Drawable.calculateDominantColor(onFinish: (Color) -> Unit) {

    val bitmap = (this as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)
    Palette.from(bitmap).generate { palette ->
        palette?.dominantSwatch?.rgb?.let { intColor ->
            onFinish(
                Color(intColor)
            )
        }
    }
}