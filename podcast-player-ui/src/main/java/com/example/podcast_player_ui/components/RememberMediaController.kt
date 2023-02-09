package com.example.podcast_player_ui.components

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.example.podcast_player_ui.data.services.getMediaSessionController
import androidx.media3.session.MediaController

@Composable
fun rememberMediaController(): MutableState<MediaController?> {
    val context = LocalContext.current
    val mediaControllerState = remember {
        mutableStateOf<MediaController?>(null)
    }

    LaunchedEffect(true) {
        mediaControllerState.value = context.getMediaSessionController()
    }

    return mediaControllerState
}

