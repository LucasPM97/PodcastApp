package com.example.podcast_details_ui.di

import com.example.podcast_details_ui.screens.podcastDetails.PodcastDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        PodcastDetailsViewModel(get())
    }
}