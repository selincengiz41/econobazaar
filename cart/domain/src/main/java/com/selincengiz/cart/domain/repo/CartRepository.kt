package com.selincengiz.cart.domain.repo

import com.selincengiz.cart.domain.model.CartUI

interface CartRepository {
    suspend fun getCart(userId: Int): CartUI
}