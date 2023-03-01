package com.example.podcast_explore_domain.useCases

import com.example.core.data_interfaces.repositories.IEpisodeRepository
import com.example.core.models.Episode
import kotlinx.coroutines.flow.Flow

class GetEpisodeHistoryUseCases(
    private val repository: IEpisodeRepository,
) {
    operator fun invoke(): Flow<List<Episode>> =
        repository.getEpisodesHistory(10)
}