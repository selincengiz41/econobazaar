package com.selincengiz.core.network.dto.cart


import com.google.gson.annotations.SerializedName

data class CartRequest(
    @SerializedName("products")
    val products: List<Product>,
    @SerializedName("userId")
    val userId: Int
)