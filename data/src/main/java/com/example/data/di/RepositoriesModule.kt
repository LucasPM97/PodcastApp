package com.example.data.di

import com.example.data.repositories.EpisodeRepository
import com.example.data.repositories.PodcastDetailsRepository
import com.example.core.data_interfaces.repositories.IEpisodeRepository
import com.example.core.data_interfaces.repositories.IPodcastRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<IPodcastRepository> {
        PodcastDetailsRepository(get(), get())
    }
    single<IEpisodeRepository> {
        EpisodeRepository(get())
    }
}