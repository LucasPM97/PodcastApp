package com.example.podcast_player_ui.di

import com.example.podcast_player_domain.di.useCasesModule
import org.koin.dsl.module

val podcastPlayerModules = module {
    includes(viewModelModule, useCasesModule, playerModule)
}