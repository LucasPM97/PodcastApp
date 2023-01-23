package com.example.podcastapp_core.di

import android.os.Looper
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.http.HttpHeader
import org.koin.dsl.module

val apiModule = module {
    single<ApolloClient> {
        check(Looper.myLooper() == Looper.getMainLooper()) {
            "Only the main thread can get the Apollo Client instance"
        }

        ApolloClient.Builder()
            .serverUrl("https://api.taddy.org/")
            .httpHeaders(
                listOf(
                    HttpHeader("X-USER-ID", "195"),
                    HttpHeader(
                        "X-API-KEY",
                        "5f83b41af00aa3a00508463bfbc6ec750d4a37a89ad306fb54224f4e01254132155cc243c387b02a8cd3886872f0fc258e"
                    )
                )
            )
            .build()
    }
}