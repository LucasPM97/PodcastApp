package com.example.data.di

import org.koin.dsl.module

val dataModules = module {
    includes(
        dataSourceModule,
        repositoryModule,
        apiModule
    )
}