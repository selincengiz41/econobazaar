package com.selincengiz.core.network.dto.category


import com.google.gson.annotations.SerializedName

data class CategoryResponseItem(
    @SerializedName("name")
    val name: String,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("url")
    val url: String
)