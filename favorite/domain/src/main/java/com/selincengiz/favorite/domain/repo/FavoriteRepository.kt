package com.selincengiz.favorite.domain.repo

import com.selincengiz.favorite.domain.model.ProductUI

interface FavoriteRepository {
    suspend fun getFavorites(): List<ProductUI>
}