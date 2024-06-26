package com.selincengiz.profile.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HairUI(
    val color: String,
    val type: String
): Parcelable
