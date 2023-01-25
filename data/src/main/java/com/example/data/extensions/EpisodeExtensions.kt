package com.example.data.extensions

import com.example.data.PodcastDetailsQuery
import com.example.podcast_details_domain.models.Episode

fun List<PodcastDetailsQuery.Episode?>.toEpisodeDomainList(): List<Episode?> = map {
    it?.toEpisodeDomain()
}

fun PodcastDetailsQuery.Episode.toEpisodeDomain(): Episode {
    return Episode(
        uuid,
        name,
        description,
        audioUrl,
        duration,
    )
}