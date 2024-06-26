package com.selincengiz.profile.presentation.update_profile

import com.selincengiz.profile.domain.model.UserUI

sealed interface UpdateProfileState {
    data object Entry : UpdateProfileState
    data object Loading : UpdateProfileState
    data class Data(val user: UserUI) : UpdateProfileState
    data class Error(val throwable: String) : UpdateProfileState
}