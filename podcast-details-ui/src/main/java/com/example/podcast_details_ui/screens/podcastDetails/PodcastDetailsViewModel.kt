package com.example.podcast_details_ui.screens.podcastDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.models.ApiResponse
import com.example.podcast_details_domain.useCases.FetchPodcastDetailsUseCase
import com.example.podcast_details_domain.useCases.GetPodcastDetailsUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class PodcastDetailsViewModel(
    private val getPodcastDetails: GetPodcastDetailsUseCase,
    private val fetchPodcastDetails: FetchPodcastDetailsUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        PodcastDetailsUiState(
            isLoading = false,
            errorMessage = ""
        )
    )
    val uiState = _uiState.asStateFlow()

    val podcastDetails = getPodcastDetails(podcastUuid = "")

    init {
        viewModelScope.launch {
            fetchData(
                podcasUuid = ""
            )
        }
    }

    private suspend fun fetchData(podcasUuid: String) {
        _uiState.update {
            it.copy(
                isLoading = true
            )
        }
        val response = fetchPodcastDetails(podcasUuid)
        when (response) {
            is ApiResponse.Error -> {
                //TODO: Show error message
                _uiState.update {
                    it.copy(
                        errorMessage = "Generic Error message"
                    )
                }
            }
            else -> {}
        }
        _uiState.update {
            it.copy(
                isLoading = false
            )
        }
    }

    data class PodcastDetailsUiState(
        val isLoading: Boolean = false,
        val errorMessage: String? = null
    )

}