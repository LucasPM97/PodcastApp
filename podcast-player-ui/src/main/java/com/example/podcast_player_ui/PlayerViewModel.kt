package com.example.podcast_player_ui

import androidx.lifecycle.ViewModel
import com.example.podcast_player_domain.useCases.GetEpisodeUseCase

class PlayerViewModel(
    getEpisodeUseCase: GetEpisodeUseCase
) : ViewModel() {
    val episode = getEpisodeUseCase("")
}