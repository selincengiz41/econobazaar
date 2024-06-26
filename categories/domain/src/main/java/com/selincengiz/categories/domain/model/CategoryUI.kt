package com.selincengiz.categories.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CategoryUI(
    val name: String,
    val slug: String,
    val url: String
) : Parcelable
