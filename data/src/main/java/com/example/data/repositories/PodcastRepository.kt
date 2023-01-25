package com.example.data.repositories

import com.example.data.datasource.IRemotePodcastDataSource
import com.example.data.extensions.toDomainPodcast
import com.example.podcast_details_domain.data_interfaces.datasource.ILocalPodcastDataSource
import com.example.podcast_details_domain.data_interfaces.repositories.IPodcastRepository
import com.example.podcast_details_domain.models.PodcastDetails
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PodcastRepository(
    private val remoteDataSource: IRemotePodcastDataSource,
    private val localDataSource: ILocalPodcastDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) : IPodcastRepository {
    override suspend fun getLocalPodcastDetails(podcastUuid: String): PodcastDetails? =
        withContext(dispatcher) {
            return@withContext localDataSource.getPodcastDetails(podcastUuid)
        }

    override suspend fun getRemotePodcastDetails(podcastUuid: String): PodcastDetails? =
        withContext(dispatcher) {
            val response = remoteDataSource.getPodcastDetails(podcastUuid)

            if (response.hasErrors()) {
                return@withContext null
            }

            return@withContext response.data?.getPodcastSeries?.toDomainPodcast()
        }

    override suspend fun storePodcastDetails(podcast: PodcastDetails) = withContext(dispatcher) {
        localDataSource.storePodcastDetails(podcast)
    }

}