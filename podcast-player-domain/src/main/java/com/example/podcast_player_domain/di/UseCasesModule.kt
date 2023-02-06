package com.example.podcast_player_domain.di

import com.example.podcast_player_domain.useCases.GetEpisodeUseCase
import org.koin.dsl.module

val useCasesModule = module {
    single {
        GetEpisodeUseCase(
            get()
        )
    }
}