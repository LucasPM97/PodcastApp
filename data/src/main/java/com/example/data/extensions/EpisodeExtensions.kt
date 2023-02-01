package com.example.data.extensions

import com.example.data.PodcastDetailsQuery
import com.example.podcast_details_domain.models.Episode

// This only works if all episodes are from the same Podcast
fun List<PodcastDetailsQuery.Episode?>.toEpisodeDomainList(
    podcastUuid: String,
    podcastImageUrl: String?
): List<Episode> =
    filterNotNull()
        .map {
            it.toEpisodeDomain(
                podcastUuid,
                podcastImageUrl
            )
        }

fun PodcastDetailsQuery.Episode.toEpisodeDomain(
    podcastUuid: String,
    podcastImageUrl: String?
): Episode {
    return Episode(
        podcastUuid = podcastUuid,
        uuid = uuid,
        name = name,
        imageUrl = if (imageUrl.isNullOrEmpty()) podcastImageUrl ?: "" else imageUrl,
        description = subtitle,
        audioUrl = audioUrl ?: videoUrl,
        duration = duration,
        datePublished = datePublished,
        seasonNumber = seasonNumber,
        episodeNumber = episodeNumber
    )
}