package com.example.core.models

data class Episode(
    val podcastUuid: String?,
    val uuid: String?,
    val name: String?,
    val imageUrl: String,
    val description: String?,
    val audioUrl: String?,
    val duration: Int?,
    val datePublished: Int?,
    val seasonNumber: Int?,
    val episodeNumber: Int?,
    val watched: Boolean = false,
    val timeWatched: Int = 0
)