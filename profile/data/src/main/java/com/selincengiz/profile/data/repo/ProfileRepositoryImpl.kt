package com.selincengiz.profile.data.repo

import com.selincengiz.core.network.dataproviders.NetworkDataProviders
import com.selincengiz.profile.data.mapper.mapToUserResponse
import com.selincengiz.profile.data.mapper.mapToUserUI
import com.selincengiz.profile.domain.model.UserUI
import com.selincengiz.profile.domain.repo.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val dataProviders: NetworkDataProviders
) : ProfileRepository {
    override suspend fun getProfile(userId: Int): UserUI {
        return dataProviders.getProfile(userId).mapToUserUI()
    }

    override suspend fun updateProfile(userId: Int, userUI: UserUI): UserUI {
        return dataProviders.updateProfile(userId, userUI.mapToUserResponse()).mapToUserUI()
    }
}