package com.example.podcast_details.data.repositories

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.example.podcast_details.PodcastDetailsQuery


interface IPodcastRepository {
    suspend fun getPodcastDetails(podcastName: String): PodcastDetailsQuery.Data?
}

class PodcastRepository(
    private val apolloClient: ApolloClient
) : IPodcastRepository {
    override suspend fun getPodcastDetails(podcastName: String): PodcastDetailsQuery.Data? {
        return try {
            apolloClient.query(PodcastDetailsQuery(podcastName)).execute().data
        } catch (ex: java.lang.Exception) {
            null
        }
    }
}