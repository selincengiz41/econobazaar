package com.selincengiz.core.network.dto.cart


import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id")
    val id: Int,
    @SerializedName("quantity")
    val quantity: Int
)