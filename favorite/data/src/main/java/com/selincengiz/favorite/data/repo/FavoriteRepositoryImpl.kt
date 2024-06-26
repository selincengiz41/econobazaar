package com.selincengiz.favorite.data.repo

import com.selincengiz.core.local.dataproviders.LocalDataProviders
import com.selincengiz.favorite.data.mappers.mapToProductUI
import com.selincengiz.favorite.domain.model.ProductUI
import com.selincengiz.favorite.domain.repo.FavoriteRepository
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val localProviders: LocalDataProviders
) : FavoriteRepository {

    override suspend fun getFavorites(): List<ProductUI> {
        return localProviders.getFavorites().map { it.mapToProductUI() }
    }
}