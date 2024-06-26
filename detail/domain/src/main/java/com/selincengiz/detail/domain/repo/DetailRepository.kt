package com.selincengiz.detail.domain.repo

import com.selincengiz.detail.domain.model.CartUI
import com.selincengiz.detail.domain.model.ProductUI

interface DetailRepository {
    suspend fun getDetailProduct(productId: Int): ProductUI

    suspend fun addFavorites(product: ProductUI)

    suspend fun deleteFavorites(product: ProductUI)

    suspend fun getFavorites(): List<ProductUI>

    suspend fun addToCart(cart: CartUI)
}