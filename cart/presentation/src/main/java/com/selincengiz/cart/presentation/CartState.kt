package com.selincengiz.cart.presentation

import com.selincengiz.cart.domain.model.CartUI

sealed interface CartState {
    data object Entry : CartState
    data object Loading : CartState
    data class Data(val cart: CartUI) : CartState
    data class Error(val throwable: String) : CartState
}