package com.example.data.datasource

import com.example.data.extensions.toLocalPodcastDetails
import com.example.podcast_details_domain.data_interfaces.datasource.ILocalPodcastDataSource
import com.example.podcast_details_domain.models.Genre
import com.example.podcast_details_domain.models.PodcastDetails
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalPodcastDataSource(
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) : ILocalPodcastDataSource {
    override suspend fun getPodcastDetails(podcastUuid: String): PodcastDetails? =
        withContext(dispatcher) {
            // TODO: Return data from Database to avoid too many api calls
            return@withContext mockPodcastDetails()
        }

    override suspend fun storePodcastDetails(podcast: PodcastDetails) = withContext(dispatcher) {
        //TODO: Store data into Database
        podcast.toLocalPodcastDetails()
        return@withContext
    }

    private fun mockPodcastDetails(): PodcastDetails {
        return PodcastDetails(
            uuid = "d682a935-ad2d-46ee-a0ac-139198b83bcc",
            name = "This American Life",
            description = "This American Life is a weekly public radio show, heard by 2.2 million people on more than 500 stations. Another 2.5 million people download the weekly podcast. It is hosted by Ira Glass, produced in collaboration with Chicago Public Media, delivered to stations by PRX The Public Radio Exchange, and has won all of the major broadcasting awards.",
            imageUrl = "https://files.thisamericanlife.org/sites/all/themes/thislife/img/tal-name-1400x1400.png",
            genres = listOf(
                Genre(
                    genreText = "fiction",
                    genreRemoteEnumString = "PODCASTSERIES_FICTION"
                )
            ),
            websiteUrl = null,
            authorName = "Juan Menguez",
            episodes = null
        )
    }
}