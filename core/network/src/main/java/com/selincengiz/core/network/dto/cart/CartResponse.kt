package com.selincengiz.core.network.dto.cart


import com.google.gson.annotations.SerializedName

data class CartResponse(
    @SerializedName("discountedTotal")
    val discountedTotal: Double,
    @SerializedName("id")
    val id: Int,
    @SerializedName("products")
    val products: List<ProductX>,
    @SerializedName("total")
    val total: Double,
    @SerializedName("totalProducts")
    val totalProducts: Int,
    @SerializedName("totalQuantity")
    val totalQuantity: Int,
    @SerializedName("userId")
    val userId: Int
)