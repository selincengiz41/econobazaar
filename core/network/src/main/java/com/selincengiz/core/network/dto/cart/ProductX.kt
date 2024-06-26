package com.selincengiz.core.network.dto.cart


import com.google.gson.annotations.SerializedName

data class ProductX(
    @SerializedName("discountPercentage")
    val discountPercentage: Double,
    @SerializedName("discountedPrice")
    val discountedPrice: Double,
    @SerializedName("id")
    val id: Int,
    @SerializedName("price")
    val price: Double,
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("thumbnail")
    val thumbnail: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("total")
    val total: Double
)