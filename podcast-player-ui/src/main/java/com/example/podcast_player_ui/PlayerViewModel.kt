package com.example.podcast_player_ui

import androidx.lifecycle.ViewModel
import com.example.core.models.Episode
import com.example.podcast_player_domain.useCases.GetEpisodeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PlayerViewModel(
    getEpisodeUseCase: GetEpisodeUseCase
) : ViewModel() {

    private val _playingEpisodeUuid = MutableStateFlow("")
    val playingEpisodeUuid = _playingEpisodeUuid.asStateFlow()
    val playingEpisode = getEpisodeUseCase(playingEpisodeUuid.value)

    fun playEpisode(episodeUuid: String) {
        _playingEpisodeUuid.update {
            episodeUuid
        }
    }

    fun clearPlaylist() {
        _playingEpisodeUuid.update { "" }
    }

    override fun onCleared() {
        super.onCleared()
        clearPlaylist()
    }
}