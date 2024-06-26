package com.selincengiz.detail.presentation

import com.selincengiz.detail.domain.model.ProductUI

sealed interface DetailState {
    data object Entry : DetailState
    data object Loading : DetailState
    data class IsFavorite(val isFavorite: Boolean) : DetailState
    data class AddCart(val message: String) : DetailState
    data class Data(val product: ProductUI) : DetailState
    data class Error(val throwable: String) : DetailState
}