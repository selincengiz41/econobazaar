package com.selincengiz.profile.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CompanyUI(
    val department: String,
    val name: String,
    val title: String
): Parcelable
