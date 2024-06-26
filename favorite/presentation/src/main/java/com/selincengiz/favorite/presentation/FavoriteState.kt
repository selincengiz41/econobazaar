package com.selincengiz.favorite.presentation

import com.selincengiz.favorite.domain.model.ProductUI

sealed interface FavoriteState {
    data object Entry : FavoriteState
    data object Loading : FavoriteState
    data class Data(val list: List<ProductUI>) : FavoriteState
    data class Error(val throwable: String) : FavoriteState
}