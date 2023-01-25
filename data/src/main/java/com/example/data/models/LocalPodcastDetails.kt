package com.example.data.models

data class LocalPodcastDetails(
    val uuid: String?,
    val name: String?,
    val description: String?,
    val imageUrl: String?,
    //using genreRemoteEnumString
    val genres: List<String>?,
    val websiteUrl: String?,
    val authorName: String?
)