package com.example.podcast_player_ui.di

import androidx.media3.common.AudioAttributes
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession
import com.example.podcast_player_ui.data.services.PodcastPlayerService
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val playerModule = module {
//    single<Player> {
//        ExoPlayer.Builder(androidContext())
//            .setAudioAttributes(AudioAttributes.DEFAULT, true)
//            .setHandleAudioBecomingNoisy(true)
//            .build()
//    }
//    single {
//        val customCallback = PodcastPlayerService.CustomMediaSessionCallback()
//        MediaSession.Builder(androidContext(), get())
//            .setCallback(customCallback)
//            .build()
//    }
}