package com.selincengiz.profile.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserUI(
    val address: AddressUI,
    val age: Int,
    val birthDate: String,
    val bloodGroup: String,
    val company: CompanyUI,
    val ein: String,
    val email: String,
    val eyeColor: String,
    val firstName: String,
    val gender: String,
    val hair: HairUI,
    val height: Double,
    val id: Int,
    val image: String,
    val ip: String,
    val lastName: String,
    val macAddress: String,
    val maidenName: String,
    val password: String,
    val phone: String,
    val role: String?,
    val ssn: String,
    val university: String,
    val userAgent: String,
    val username: String,
    val weight: Double
) : Parcelable
