package com.example.podcast_player_ui.models

enum class ComponentSize {
    None,
    Small,
    FullScreen
}

fun ComponentSize.switchSize() = when (this) {
    ComponentSize.Small -> ComponentSize.FullScreen
    ComponentSize.FullScreen -> ComponentSize.Small
    else -> ComponentSize.None
}