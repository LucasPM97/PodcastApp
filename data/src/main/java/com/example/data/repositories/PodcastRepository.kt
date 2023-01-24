package com.example.data.repositories

import com.apollographql.apollo3.api.ApolloResponse
import com.example.data.PodcastDetailsQuery
import com.example.data.datasource.ILocalPodcastDataSource
import com.example.data.datasource.IRemotePodcastDataSource

interface IPodcastRepository {
    suspend fun getPodcastDetails(podcastUuid: String): ApolloResponse<PodcastDetailsQuery.Data>
    suspend fun storePodcastDetails(podcast: PodcastDetailsQuery.GetPodcastSeries)
}

class PodcastRepository(
    private val remoteDataSource: IRemotePodcastDataSource,
    private val localDataSource: ILocalPodcastDataSource
) : IPodcastRepository {
    override suspend fun getPodcastDetails(podcastUuid: String): ApolloResponse<PodcastDetailsQuery.Data> {
        return remoteDataSource.getPodcastDetails(podcastUuid)
    }

    override suspend fun storePodcastDetails(podcast: PodcastDetailsQuery.GetPodcastSeries) {
        localDataSource.storePodcastDetails(podcast)
    }
}