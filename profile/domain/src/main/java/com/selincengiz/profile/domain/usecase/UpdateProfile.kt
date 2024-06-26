package com.selincengiz.profile.domain.usecase

import com.selincengiz.core.common.UiEvent
import com.selincengiz.profile.domain.model.UserUI
import com.selincengiz.profile.domain.repo.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UpdateProfile @Inject constructor(
    private val profileRepository: ProfileRepository
) {
    operator fun invoke(userId: Int, userUI: UserUI) = flow {
        emit(UiEvent.Loading)
        emit(UiEvent.Success(profileRepository.updateProfile(userId, userUI)))
    }.catch {
        emit(UiEvent.Error(it.message.toString()))
    }.flowOn(Dispatchers.IO)
}