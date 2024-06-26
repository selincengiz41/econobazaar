package com.selincengiz.core.network

import com.selincengiz.core.common.Constants.ADD_TO_CART
import com.selincengiz.core.common.Constants.GET_CART
import com.selincengiz.core.common.Constants.GET_CATEGORIES
import com.selincengiz.core.common.Constants.GET_CATEGORIES_PRODUCTS
import com.selincengiz.core.common.Constants.GET_PRODUCTS
import com.selincengiz.core.common.Constants.GET_PRODUCT_DETAIL
import com.selincengiz.core.common.Constants.GET_PROFILE
import com.selincengiz.core.common.Constants.SEARCH_PRODUCTS
import com.selincengiz.core.common.Constants.UPDATE_PROFILE
import com.selincengiz.core.network.dto.cart.CartRequest
import com.selincengiz.core.network.dto.cart.CartResponse
import com.selincengiz.core.network.dto.category.CategoryResponse
import com.selincengiz.core.network.dto.product.Product
import com.selincengiz.core.network.dto.product.ProductResponse
import com.selincengiz.core.network.dto.user.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET(GET_PRODUCTS)
    suspend fun getProducts(
        @Query("skip") skip: Int,
        @Query("limit") limit: Int = 10
    ): ProductResponse

    @GET(GET_PRODUCT_DETAIL)
    suspend fun getProductDetail(
        @Path("productId") productId: Int,
    ): Product

    @GET(SEARCH_PRODUCTS)
    suspend fun searchProducts(
        @Query("q") query: String,
        @Query("skip") skip: Int,
        @Query("limit") limit: Int = 10
    ): ProductResponse

    @GET(GET_CATEGORIES)
    suspend fun getCategories(): CategoryResponse

    @GET(GET_CATEGORIES_PRODUCTS)
    suspend fun getCategoriesProducts(
        @Path("category") category: String,
        @Query("skip") skip: Int,
        @Query("limit") limit: Int = 10
    ): ProductResponse

    @POST(ADD_TO_CART)
    suspend fun addToCart(@Body cart: CartRequest): CartResponse

    @GET(GET_CART)
    suspend fun getCart(@Path("userId") userId: Int): CartResponse

    @GET(GET_PROFILE)
    suspend fun getProfile(@Path("userId") userId: Int): UserResponse

    @PUT(UPDATE_PROFILE)
    suspend fun updateProfile(@Path("userId") userId: Int, @Body user: UserResponse): UserResponse

}