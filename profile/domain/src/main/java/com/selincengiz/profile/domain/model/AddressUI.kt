package com.selincengiz.profile.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AddressUI(
    val address: String,
    val city: String,
    val country: String,
    val postalCode: String,
    val state: String,
    val stateCode: String
) : Parcelable
