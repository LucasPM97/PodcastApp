package com.example.podcast_player_domain.useCases

import com.example.core.data_interfaces.repositories.IEpisodeRepository
import com.example.core.models.Episode
import kotlinx.coroutines.flow.Flow

class GetEpisodeUseCase(
    private val repository: IEpisodeRepository
) {
    operator fun invoke(episodeUuid: String): Flow<Episode?> {
        return repository.getEpisodeByUuidFlow(episodeUuid)
    }
}