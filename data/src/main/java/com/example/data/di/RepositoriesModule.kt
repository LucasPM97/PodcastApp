package com.example.data.di

import com.example.data.repositories.PodcastRepository
import com.example.podcast_details_domain.data_interfaces.repositories.IPodcastRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<IPodcastRepository> {
        PodcastRepository(get(), get())
    }
}