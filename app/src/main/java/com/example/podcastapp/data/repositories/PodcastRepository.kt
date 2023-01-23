package com.example.podcastapp.data.repositories

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.example.podcastapp.PodcastDetailsQuery


interface IPodcastRepository {
    suspend fun getPodcastDetails(podcastName: String): ApolloResponse<PodcastDetailsQuery.Data>
}

class PodcastRepository(
    private val apolloClient: ApolloClient
) : IPodcastRepository {
    override suspend fun getPodcastDetails(podcastName: String): ApolloResponse<PodcastDetailsQuery.Data> {
        return try {
            apolloClient.query(PodcastDetailsQuery(podcastName)).execute()
        } catch (ex: java.lang.Exception) {
            throw java.lang.Exception("Something went wrong")
        }
    }
}