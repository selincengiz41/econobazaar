package com.selincengiz.core.common

sealed class UiEvent<out T : Any> {
    data object Loading : UiEvent<Nothing>()
    data class Success<out T : Any>(val data: T) : UiEvent<T>()
    data class Error(val message: String) : UiEvent<Nothing>()
}
