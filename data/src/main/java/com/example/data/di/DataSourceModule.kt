package com.example.data.di

import com.example.data.datasource.ILocalPodcastDataSource
import com.example.data.datasource.IRemotePodcastDataSource
import com.example.data.datasource.LocalPodcastDataSource
import com.example.data.datasource.RemotePodcastDataSource
import com.example.data.repositories.IPodcastRepository
import com.example.data.repositories.PodcastRepository
import org.koin.dsl.module

val dataSourceModule = module {
    single<IRemotePodcastDataSource> {
        RemotePodcastDataSource(get())
    }
    single<ILocalPodcastDataSource> {
        LocalPodcastDataSource()
    }
}