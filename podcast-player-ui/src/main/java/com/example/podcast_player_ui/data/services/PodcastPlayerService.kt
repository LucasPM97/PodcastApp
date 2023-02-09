package com.example.podcast_player_ui.data.services

import android.content.ComponentName
import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.session.*
import com.google.common.util.concurrent.Futures
import com.google.common.util.concurrent.ListenableFuture
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

    class CustomMediaSessionCallback() : MediaSession.Callback {
        override fun onAddMediaItems(
            mediaSession: MediaSession,
            controller: MediaSession.ControllerInfo,
            mediaItems: MutableList<MediaItem>
        ): ListenableFuture<MutableList<MediaItem>> {
            val updatedMediaItems = mediaItems.map { it.buildUpon().setUri(it.mediaId).build() }.toMutableList()
            return Futures.immediateFuture(updatedMediaItems)
        }
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