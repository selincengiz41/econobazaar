package com.selincengiz.core.network.dataproviders


import com.selincengiz.core.network.ApiService
import com.selincengiz.core.network.dto.cart.CartRequest
import com.selincengiz.core.network.dto.user.UserResponse
import javax.inject.Inject

class NetworkDataProviders @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getProducts(
        skip: Int,
        limit: Int = 10
    ) = apiService.getProducts(skip = skip, limit = limit)

    suspend fun getProductDetail(
        productId: Int
    ) = apiService.getProductDetail(productId = productId)

    suspend fun searchProducts(
        query: String,
        skip: Int,
        limit: Int = 10
    ) = apiService.searchProducts(query = query, skip = skip, limit = limit)

    suspend fun getCategories() = apiService.getCategories()

    suspend fun getCategoriesProducts(
        category: String,
        skip: Int,
        limit: Int = 10
    ) = apiService.getCategoriesProducts(category = category, skip = skip, limit = limit)

    suspend fun addToCart(cart: CartRequest) = apiService.addToCart(cart = cart)

    suspend fun getCart(userId: Int) = apiService.getCart(userId = userId)

    suspend fun getProfile(userId: Int) = apiService.getProfile(userId = userId)

    suspend fun updateProfile(userId: Int, user: UserResponse) =
        apiService.updateProfile(userId = userId, user = user)

}