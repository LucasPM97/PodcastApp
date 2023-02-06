package com.example.data.repositories

import com.example.core.models.ApiResponse
import com.example.data.datasource.IRemotePodcastDataSource
import com.example.data.extensions.toDomainPodcast
import com.example.core.data_interfaces.datasource.ILocalPodcastDataSource
import com.example.core.data_interfaces.repositories.IPodcastRepository
import com.example.core.models.podcastDetails.PodcastDetails
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class PodcastRepository(
    private val remoteDataSource: IRemotePodcastDataSource,
    private val localDataSource: ILocalPodcastDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) : IPodcastRepository {
    override fun getLocalPodcastDetails(podcastUuid: String): Flow<PodcastDetails?> =
        localDataSource.getPodcastDetails(podcastUuid)

    override suspend fun getRemotePodcastDetails(podcastUuid: String): ApiResponse<PodcastDetails> =
        withContext(dispatcher) {
            val result = remoteDataSource.getPodcastDetails(podcastUuid)

            return@withContext if (result == null)
                ApiResponse.Error()
            else
                ApiResponse.Success(
                    data = result.toDomainPodcast()
                )
        }

    override suspend fun storePodcastDetails(podcast: PodcastDetails) = withContext(dispatcher) {
        localDataSource.storePodcastDetails(podcast)
    }

}