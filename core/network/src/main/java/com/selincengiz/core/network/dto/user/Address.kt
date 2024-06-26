package com.selincengiz.core.network.dto.user


import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("address")
    val address: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("postalCode")
    val postalCode: String,
    @SerializedName("state")
    val state: String,
    @SerializedName("stateCode")
    val stateCode: String
)