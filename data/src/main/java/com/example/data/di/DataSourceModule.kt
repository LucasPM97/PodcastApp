package com.example.data.di

import com.example.data.datasource.IRemotePodcastDataSource
import com.example.data.datasource.LocalPodcastDataSource
import com.example.data.datasource.RemotePodcastDataSource
import com.example.podcast_details_domain.data_interfaces.datasource.ILocalPodcastDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single<IRemotePodcastDataSource> {
        RemotePodcastDataSource(get())
    }
    single<ILocalPodcastDataSource> {
        LocalPodcastDataSource()
    }
}