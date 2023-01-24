package com.example.data.datasource

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloResponse
import com.example.data.PodcastDetailsQuery
import com.example.data.SearchSeriesForTermQuery

interface IRemotePodcastDataSource {
    suspend fun getPodcastDetails(podcastUuid: String): ApolloResponse<PodcastDetailsQuery.Data>
    suspend fun getAllPodcastsForTerm(
        term: String,
        pageLimit: Int
    ): ApolloResponse<SearchSeriesForTermQuery.Data>
}

class RemotePodcastDataSource(
    private val apolloClient: ApolloClient
) : IRemotePodcastDataSource {
    override suspend fun getPodcastDetails(podcastUuid: String): ApolloResponse<PodcastDetailsQuery.Data> {
        return apolloClient.query(PodcastDetailsQuery(podcastUuid)).execute()
    }

    override suspend fun getAllPodcastsForTerm(
        term: String,
        pageLimit: Int
    ): ApolloResponse<SearchSeriesForTermQuery.Data> {
        return apolloClient.query(
            SearchSeriesForTermQuery(
                term,
                limitPerPage = pageLimit
            )
        ).execute()
    }

}