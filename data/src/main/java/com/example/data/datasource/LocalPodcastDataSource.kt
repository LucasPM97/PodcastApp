package com.example.data.datasource

import com.example.data.extensions.toDomainPodcastDetails
import com.example.data.extensions.toLocalPodcastDetails
import com.example.data.models.LocalPodcastDetails
import com.example.podcast_details_domain.data_interfaces.datasource.ILocalPodcastDataSource
import com.example.podcast_details_domain.models.PodcastDetails
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class LocalPodcastDataSource(
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) : ILocalPodcastDataSource {
    override fun getPodcastDetails(podcastUuid: String): Flow<PodcastDetails?> = flow {
        emit(mockLocalPodcastDetails())
    }.map {
        it.toDomainPodcastDetails()
    }

    override suspend fun storePodcastDetails(podcast: PodcastDetails) = withContext(dispatcher) {
        //TODO: Store data into Database
        podcast.toLocalPodcastDetails()
        return@withContext
    }

    private fun mockLocalPodcastDetails(): LocalPodcastDetails {
        return LocalPodcastDetails(
            uuid = "d682a935-ad2d-46ee-a0ac-139198b83bcc",
            name = "This American Life",
            description = "This American Life is a weekly public radio show, heard by 2.2 million people on more than 500 stations. Another 2.5 million people download the weekly podcast. It is hosted by Ira Glass, produced in collaboration with Chicago Public Media, delivered to stations by PRX The Public Radio Exchange, and has won all of the major broadcasting awards.",
            imageUrl = "https://files.thisamericanlife.org/sites/all/themes/thislife/img/tal-name-1400x1400.png",
            genres = listOf(
                "PODCASTSERIES_FICTION"
            ),
            websiteUrl = null,
            authorName = "Juan Menguez"
        )
    }
}