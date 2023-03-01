package com.example.podcast_explore_domain.interfaces

import com.example.core.models.ApiResponse
import com.example.core.models.Genre
import com.example.core.models.Podcast

interface IPodcastGenreRepository {
    fun getLocalPodcastsByGenre(genre: Genre): List<Podcast>
    suspend fun getRemotePodcastsByGenre(genre: Genre): ApiResponse<List<Podcast>>
    suspend fun storePodcastListByGenre(genre: Genre, podcastList: List<Podcast>)
}