package com.example.data.di

import com.example.data.repositories.IPodcastRepository
import com.example.data.repositories.PodcastRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<IPodcastRepository> {
        PodcastRepository(get(), get())
    }
}