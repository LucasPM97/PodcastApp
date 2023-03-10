package com.example.core.models

sealed interface ApiResponse<T> {
    data class Success<T>(val data: T?) : ApiResponse<T>
    data class Error<T>(val data: T? = null) : ApiResponse<T>
}