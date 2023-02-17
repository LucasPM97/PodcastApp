package com.example.podcast_player_ui.components

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import com.example.podcast_player_ui.data.services.getMediaSessionController
import androidx.media3.session.MediaController
import com.example.core_ui.components.LifecycleEvent
import kotlinx.coroutines.launch

@Composable
fun rememberMediaController(): MediaController? {
    val context = LocalContext.current

    var mediaControllerState by remember {
        mutableStateOf<MediaController?>(null)
    }

    var initialized by rememberSaveable {
        mutableStateOf(
            false
        )
    }

    val coroutineScope = rememberCoroutineScope()
    fun initMediaController() {
        if (!initialized) {
            initialized = true
            coroutineScope.launch {
                mediaControllerState = context.getMediaSessionController()
            }
        }
    }

    fun releaseMediaController() {
        if (initialized) {
            initialized = false
            mediaControllerState?.release()
        }

    }

    LifecycleEvent(onEvent = { lifecycle ->
        when (lifecycle) {
            Lifecycle.Event.ON_START -> initMediaController()
            Lifecycle.Event.ON_STOP -> releaseMediaController()
            else -> {}
        }
    })

    DisposableEffect(true) {
        initMediaController()
        onDispose {
            releaseMediaController()
        }
    }

    return mediaControllerState
}

