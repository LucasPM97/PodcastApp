package com.example.podcast_explore_domain.useCases

import com.example.core.models.ApiResponse
import com.example.core.models.Genre
import com.example.podcast_explore_domain.interfaces.IPodcastGenreRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetRecommendedPodcastsByGenreUseCases(
    private val repository: IPodcastGenreRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) {

    suspend operator fun invoke(genre: Genre): ApiResponse<Nothing> =
        withContext(dispatcher) {

            val apiResponseSucceeded = fetchPodcastDetails(genre)

            return@withContext if (apiResponseSucceeded) {
                //TODO: Returns Get data on DB
                ApiResponse.Success(null)
            } else {
                //TODO: Returns Get data on DB
                ApiResponse.Error(null)
            }

        }

    private suspend fun fetchPodcastDetails(genre: Genre): Boolean {
        //TODO: Start using remote data
//        return true
        val response = repository.getRemotePodcastsByGenre(genre)

        return when (response) {
            is ApiResponse.Success -> {
                response.data?.let { data ->
                    repository.storePodcastListByGenre(genre, data)
                }
                true
            }
            is ApiResponse.Error -> {
                false
            }
        }
    }
}