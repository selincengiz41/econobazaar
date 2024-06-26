package com.selincengiz.detail.data.repo

import com.selincengiz.core.local.dataproviders.LocalDataProviders
import com.selincengiz.core.network.dataproviders.NetworkDataProviders
import com.selincengiz.core.network.dto.cart.CartRequest
import com.selincengiz.detail.data.mapper.mapToCartRequest
import com.selincengiz.detail.data.mapper.mapToProductRoom
import com.selincengiz.detail.data.mapper.mapToProductUI
import com.selincengiz.detail.domain.model.CartUI
import com.selincengiz.detail.domain.model.ProductUI
import com.selincengiz.detail.domain.repo.DetailRepository
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    private val dataProviders: NetworkDataProviders,
    private val localProviders: LocalDataProviders
) : DetailRepository {
    override suspend fun getDetailProduct(productId: Int): ProductUI {
        return dataProviders.getProductDetail(productId).mapToProductUI()
    }

    override suspend fun addFavorites(product: ProductUI) {
        localProviders.addFavorites(product.mapToProductRoom())
    }

    override suspend fun deleteFavorites(product: ProductUI) {
        localProviders.deleteFavorites(product.mapToProductRoom())
    }

    override suspend fun getFavorites(): List<ProductUI> {
        return localProviders.getFavorites().map { it.mapToProductUI() }
    }

    override suspend fun addToCart(cart: CartUI) {
        dataProviders.addToCart(cart.mapToCartRequest())
    }
}