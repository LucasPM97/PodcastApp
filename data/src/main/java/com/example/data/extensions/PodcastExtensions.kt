package com.example.data.extensions

import com.example.data.PodcastDetailsQuery
import com.example.podcast_details_domain.models.PodcastDetails

fun PodcastDetailsQuery.GetPodcastSeries.toDomainPodcast(): PodcastDetails {
    return PodcastDetails(
        uuid,
        name,
        description,
        imageUrl,
        genres?.toGenreDomainList(),
        websiteUrl,
        authorName,
        episodes?.toEpisodeDomainList(
            podcastUuid = uuid ?: ""
        )
    )
}