package com.example.podcast_details_domain.models

data class Episode(
    val uuid: String?,
    val name: String?,
    val description: String?,
    val audioUrl: String?,
    val duration: Int?,
)