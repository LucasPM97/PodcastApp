package com.example.podcast_details_ui.screens.podcastDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.podcast_details_domain.models.ApiResponse
import com.example.podcast_details_domain.models.PodcastDetails
import com.example.podcast_details_domain.useCases.GetPodcastDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PodcastDetailsViewModel(
    val getPodcastDetails: GetPodcastDetailsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<PodcastDetailsUiState>(
        PodcastDetailsUiState(
            podcastDetails = null,
            isLoading = false,
            errorMessage = ""
        )
    )
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            updatePodcastDetails()
        }
    }

    private suspend fun updatePodcastDetails() {
        _uiState.update {
            it.copy(
                isLoading = true
            )
        }
        val response = getPodcastDetails("")
        when (response) {
            is ApiResponse.Success -> {
                _uiState.update {
                    it.copy(
                        podcastDetails = response.data
                    )
                }
            }
            is ApiResponse.Error -> {
                //TODO: Show error message
                _uiState.update {
                    it.copy(
                        errorMessage = "Generic Error message"
                    )
                }
            }
        }
        _uiState.update {
            it.copy(
                isLoading = false
            )
        }
    }

    data class PodcastDetailsUiState(
        val podcastDetails: PodcastDetails?,
        val isLoading: Boolean,
        val errorMessage: String
    )

}