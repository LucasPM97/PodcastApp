package com.example.podcastapp.di

import com.example.podcastapp.data.repositories.IPodcastRepository
import com.example.podcastapp.data.repositories.PodcastRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<IPodcastRepository> {
        PodcastRepository(
            get()
        )
    }
}