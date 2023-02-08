package com.example.podcast_player_data.di

import androidx.media3.common.AudioAttributes
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
            .build()
    }
    single {
        MediaSession.Builder(androidContext(), get())
            .build()
    }
}