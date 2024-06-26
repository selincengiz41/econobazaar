package com.selincengiz.profile.presentation.get_profile

import com.selincengiz.profile.domain.model.UserUI

sealed interface ProfileState{
    data object Entry : ProfileState
    data object Loading : ProfileState
    data class Data(val user: UserUI) : ProfileState
    data class Error(val throwable: String) : ProfileState
}