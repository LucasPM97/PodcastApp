package com.example.core.models.podcastDetails

import com.example.core.models.Episode
import com.example.core.models.Genre

data class PodcastDetails(
    val uuid: String?,
    val name: String?,
    val description: String?,
    val imageUrl: String?,
    val genres: List<Genre>?,
    val websiteUrl: String?,
    val authorName: String?,
    val episodes: List<Episode>?,
)