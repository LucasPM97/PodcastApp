package com.example.data.di

import com.example.data.datasource.IRemotePodcastDataSource
import com.example.data.datasource.LocalEpisodeDataSource
import com.example.data.datasource.LocalPodcastDataSource
import com.example.data.datasource.RemotePodcastDataSource
import com.example.core.data_interfaces.datasource.ILocalEpisodeDataSource
import com.example.core.data_interfaces.datasource.ILocalPodcastDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single<IRemotePodcastDataSource> {
        RemotePodcastDataSource(get())
    }
    single<ILocalPodcastDataSource> {
        LocalPodcastDataSource()
    }
    single<ILocalEpisodeDataSource> {
        LocalEpisodeDataSource()
    }
}