package com.example.podcast_details.di

import com.example.podcast_details.data.repositories.IPodcastRepository
import com.example.podcast_details.data.repositories.PodcastRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<IPodcastRepository> {
        PodcastRepository(
            get()
        )
    }
}