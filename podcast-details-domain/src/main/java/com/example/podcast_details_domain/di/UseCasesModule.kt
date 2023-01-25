package com.example.podcast_details_domain.di

import com.example.podcast_details_domain.useCases.GetPodcastDetailsUseCase
import org.koin.dsl.module

val useCasesModule = module {
    single {
        GetPodcastDetailsUseCase(get())
    }
}