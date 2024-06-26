package com.selincengiz.core.network.dto.user


import com.google.gson.annotations.SerializedName

data class Company(
    @SerializedName("department")
    val department: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("title")
    val title: String
)