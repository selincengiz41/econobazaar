package com.selincengiz.core.local.dataproviders


import com.selincengiz.core.local.ProductDao
import com.selincengiz.core.local.dto.ProductRoom
import javax.inject.Inject

class LocalDataProviders @Inject constructor(
    private val productDao: ProductDao
) {
    suspend fun getFavorites(): List<ProductRoom> = productDao.getFavorites()

    suspend fun addFavorites(product: ProductRoom) = productDao.addFavorites(product)

    suspend fun deleteFavorites(product: ProductRoom) = productDao.deleteFavorites(product)
}