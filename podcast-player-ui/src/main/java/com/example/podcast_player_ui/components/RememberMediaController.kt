package com.example.podcast_player_ui.components

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import com.example.podcast_player_ui.data.services.getMediaSessionController
import androidx.media3.session.MediaController
import com.example.core_ui.components.rememberLifecycleState
import kotlinx.coroutines.launch

@Composable
fun rememberMediaController(): MutableState<MediaController?> {
    val context = LocalContext.current
    val mediaControllerState = remember {
        mutableStateOf<MediaController?>(null)
    }

    val lifecycle by rememberLifecycleState()
    LaunchedEffect(lifecycle) {
        when (lifecycle) {
            Lifecycle.Event.ON_PAUSE -> {
                mediaControllerState.value?.release()
            }
            else -> {}
        }
    }

    val coroutineScope = rememberCoroutineScope()
    DisposableEffect(true) {
        coroutineScope.launch {
            mediaControllerState.value = context.getMediaSessionController()
        }
        onDispose {
            mediaControllerState.value?.release()
        }
    }

    return mediaControllerState
}

