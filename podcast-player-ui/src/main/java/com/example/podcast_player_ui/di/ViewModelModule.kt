package com.example.podcast_player_ui.di

import com.example.podcast_player_ui.PlayerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        PlayerViewModel(
            get()
        )
    }
}