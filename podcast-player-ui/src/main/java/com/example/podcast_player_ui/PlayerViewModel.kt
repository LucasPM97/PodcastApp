package com.example.podcast_player_ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.models.Episode
import com.example.podcast_player_domain.useCases.GetEpisodeUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class PlayerViewModel(
    private val getEpisodeUseCase: GetEpisodeUseCase
) : ViewModel() {

    private var _getEpisodeDataJob: Job? = null
    private val _episodeState = MutableStateFlow<Episode?>(null)
    val episode = _episodeState.asStateFlow()

    fun playEpisode(episodeUuid: String) {
        _getEpisodeDataJob?.cancel()
        _getEpisodeDataJob = viewModelScope.launch {
            getEpisodeUseCase(episodeUuid)
                .collect { episode ->
                    _episodeState.update { episode }
                }
        }
    }

    fun clearPlaylist() {
        _getEpisodeDataJob?.cancel()
        _episodeState.update { null }
    }

    override fun onCleared() {
        super.onCleared()
        clearPlaylist()
    }
}