package com.example.data.datasource

import com.apollographql.apollo3.ApolloClient
import com.example.data.PodcastDetailsQuery

interface ILocalPodcastDataSource {
    suspend fun getPodcastDetails(podcastUuid: String): PodcastDetailsQuery.GetPodcastSeries?
    suspend fun storePodcastDetails(podcast: PodcastDetailsQuery.GetPodcastSeries)
}

class LocalPodcastDataSource : ILocalPodcastDataSource {
    override suspend fun getPodcastDetails(podcastUuid: String): PodcastDetailsQuery.GetPodcastSeries? {
        // TODO: Return data from Database to avoid too many api calls
        return null
    }

    override suspend fun storePodcastDetails(podcast: PodcastDetailsQuery.GetPodcastSeries) {
        //TODO: Store data into Database
    }
}