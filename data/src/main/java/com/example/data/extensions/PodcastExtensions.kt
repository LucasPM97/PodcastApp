package com.example.data.extensions

import com.example.core.type.Genre
import com.example.data.PodcastDetailsQuery
import com.example.data.models.LocalPodcastDetails
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
            podcastUuid = uuid ?: "",
            podcastImageUrl = imageUrl
        )
    )
}

fun PodcastDetails.toLocalPodcastDetails(): LocalPodcastDetails {
    return LocalPodcastDetails(
        uuid,
        name,
        description,
        imageUrl,
        genres?.map {
            it.genreRemoteEnumString
        },
        websiteUrl,
        authorName
    )
}

fun LocalPodcastDetails.toDomainPodcastDetails(): PodcastDetails {
    return PodcastDetails(
        uuid,
        name,
        description,
        imageUrl,
        genres?.map {
            Genre.valueOf(it)
        }?.toGenreDomainList(),
        websiteUrl,
        authorName,
        episodes = null
    )
}