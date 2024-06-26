package com.selincengiz.profile.domain.repo

import com.selincengiz.profile.domain.model.UserUI

interface ProfileRepository {

    suspend fun getProfile(userId: Int) : UserUI

    suspend fun updateProfile(userId: Int, userUI: UserUI) : UserUI
}