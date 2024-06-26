package com.selincengiz.profile.data.mapper

import com.selincengiz.core.network.dto.user.Address
import com.selincengiz.core.network.dto.user.Company
import com.selincengiz.core.network.dto.user.Hair
import com.selincengiz.core.network.dto.user.UserResponse
import com.selincengiz.profile.domain.model.AddressUI
import com.selincengiz.profile.domain.model.CompanyUI
import com.selincengiz.profile.domain.model.HairUI
import com.selincengiz.profile.domain.model.UserUI

fun UserResponse.mapToUserUI(): UserUI {
    return UserUI(
        address.mapToAddressUI(),
        age,
        birthDate,
        bloodGroup,
        company.mapToCompanyUI(),
        ein,
        email,
        eyeColor,
        firstName,
        gender,
        hair.mapToHairUI(),
        height,
        id,
        image,
        ip,
        lastName,
        macAddress,
        maidenName,
        password,
        phone,
        role,
        ssn,
        university,
        userAgent,
        username,
        weight,
    )
}

fun UserUI.mapToUserResponse(): UserResponse {
    return UserResponse(
        address.mapToAddress(),
        age,
        birthDate,
        bloodGroup,
        company.mapToCompany(),
        ein,
        email,
        eyeColor,
        firstName,
        gender,
        hair.mapToHair(),
        height,
        id,
        image,
        ip,
        lastName,
        macAddress,
        maidenName,
        password,
        phone,
        role,
        ssn,
        university,
        userAgent,
        username,
        weight,
    )
}

fun Address.mapToAddressUI(): AddressUI {
    return AddressUI(
        address,
        city,
        country,
        postalCode,
        state,
        stateCode
    )
}

fun AddressUI.mapToAddress(): Address {
    return Address(
        address,
        city,
        country,
        postalCode,
        state,
        stateCode
    )
}

fun Company.mapToCompanyUI(): CompanyUI {
    return CompanyUI(
        department,
        name,
        title
    )
}

fun CompanyUI.mapToCompany(): Company {
    return Company(
        department,
        name,
        title
    )
}

fun Hair.mapToHairUI(): HairUI {
    return HairUI(
        color,
        type
    )
}

fun HairUI.mapToHair(): Hair {
    return Hair(
        color,
        type
    )
}