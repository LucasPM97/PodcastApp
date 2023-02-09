package com.example.podcast_player_data.di

import androidx.media3.common.AudioAttributes
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val playerModule = module {
    single<Player> {
        ExoPlayer.Builder(androidContext())
            .setAudioAttributes(AudioAttributes.DEFAULT, true)
            .setHandleAudioBecomingNoisy(true)
            .build().apply {
                val mediaItem =
                    MediaItem.fromUri("https://traffic.megaphone.fm/GLT5025099642.mp3?updated=1511216902")
                setMediaItem(mediaItem)
            }
    }
    single {
        MediaSession.Builder(androidContext(), get())
            .build()
    }
}