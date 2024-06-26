package com.selincengiz.onboarding.domain.di

import com.selincengiz.onboarding.domain.repo.LocalUserManager
import com.selincengiz.onboarding.domain.usecase.AppEntryUseCase
import com.selincengiz.onboarding.domain.usecase.ReadAppEntry
import com.selincengiz.onboarding.domain.usecase.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainLayerModule {

    @Provides
    fun provideAppEntryUseCase(
        localUserManager: LocalUserManager
    ) = AppEntryUseCase(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )
}