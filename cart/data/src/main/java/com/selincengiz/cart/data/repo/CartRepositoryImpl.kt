package com.selincengiz.cart.data.repo

import com.selincengiz.cart.data.mapper.mapToCartUI
import com.selincengiz.cart.domain.model.CartUI
import com.selincengiz.cart.domain.repo.CartRepository
import com.selincengiz.core.network.dataproviders.NetworkDataProviders
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val dataProviders: NetworkDataProviders
) : CartRepository {
    override suspend fun getCart(userId: Int): CartUI {
        return dataProviders.getCart(userId = userId).mapToCartUI()
    }
}