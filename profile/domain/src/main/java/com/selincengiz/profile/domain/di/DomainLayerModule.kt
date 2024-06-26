package com.selincengiz.profile.domain.di

import com.selincengiz.profile.domain.repo.ProfileRepository
import com.selincengiz.profile.domain.usecase.GetProfile
import com.selincengiz.profile.domain.usecase.ProfileUseCase
import com.selincengiz.profile.domain.usecase.UpdateProfile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainLayerModule {
    @Provides
    fun provideProfileUseCase(profileRepository: ProfileRepository): ProfileUseCase =
        ProfileUseCase(
            getProfile = GetProfile(profileRepository),
            updateProfile = UpdateProfile(profileRepository)
        )
}