package com.example.core.di

import org.koin.dsl.module

val coreModules = module {
    includes(
        apiModule
    )
}