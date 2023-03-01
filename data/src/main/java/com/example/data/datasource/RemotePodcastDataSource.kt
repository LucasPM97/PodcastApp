package com.example.data.datasource

import com.apollographql.apollo3.ApolloClient
import com.example.data.PodcastDetailsQuery
import com.example.data.SearchSeriesForTermQuery
import com.example.data.extensions.safeQueryExecute

interface IRemotePodcastDataSource {
    suspend fun getPodcastDetails(podcastUuid: String): PodcastDetailsQuery.GetPodcastSeries?
    suspend fun getAllPodcastsForTerm(
        term: String,
        pageLimit: Int
    ): SearchSeriesForTermQuery.SearchForTerm?
}

class RemotePodcastDataSource(
    private val apolloClient: ApolloClient
) : IRemotePodcastDataSource {
    override suspend fun getPodcastDetails(podcastUuid: String): PodcastDetailsQuery.GetPodcastSeries? {
        return apolloClient.safeQueryExecute(PodcastDetailsQuery(podcastUuid))?.getPodcastSeries
    }

    override suspend fun getAllPodcastsForTerm(
        term: String,
        pageLimit: Int
    ): SearchSeriesForTermQuery.SearchForTerm? = apolloClient.safeQueryExecute(
        SearchSeriesForTermQuery(
            term,
            limitPerPage = pageLimit
        )
    )?.searchForTerm

}