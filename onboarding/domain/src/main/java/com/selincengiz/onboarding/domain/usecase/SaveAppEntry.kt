package com.selincengiz.onboarding.domain.usecase

import com.selincengiz.onboarding.domain.repo.LocalUserManager

class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {
    suspend operator fun invoke() {
        localUserManager.saveAppEntry()
    }
}