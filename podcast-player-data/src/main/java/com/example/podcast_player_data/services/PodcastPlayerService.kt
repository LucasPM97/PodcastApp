package com.example.podcast_player_data.services

import android.content.ComponentName
import android.content.Context
import androidx.media3.common.Player
import androidx.media3.session.MediaController
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService
import androidx.media3.session.SessionToken
import kotlinx.coroutines.guava.await
import org.koin.android.ext.android.inject

class PodcastPlayerService : MediaSessionService() {

    private val player: Player by inject()
    private val mediaSession: MediaSession by inject()

    override fun onGetSession(controllerInfo: MediaSession.ControllerInfo): MediaSession? =
        mediaSession


    override fun onDestroy() {
        mediaSession.run {
            player.release()
            release()
        }
        super.onDestroy()
    }
}

suspend fun Context.getMediaSessionController(): MediaController {
    val sessionToken =
        SessionToken(this, ComponentName(this, PodcastPlayerService::class.java))

    val controllerFuture = MediaController.Builder(
        this,
        sessionToken
    ).buildAsync()

    return controllerFuture.await()
}