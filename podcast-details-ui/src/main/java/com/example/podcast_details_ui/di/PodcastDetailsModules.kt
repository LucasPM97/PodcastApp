package com.example.podcast_details_ui.di

import com.example.podcast_details_domain.di.useCasesModule
import org.koin.dsl.module

val podcastDetailsModules = module {
    includes(
        viewModelModule,
        useCasesModule
    )
}