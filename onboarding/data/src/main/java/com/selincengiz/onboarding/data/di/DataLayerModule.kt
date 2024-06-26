package com.selincengiz.onboarding.data.di

import android.content.Context
import com.selincengiz.onboarding.data.repo.LocalUserManagerImpl
import com.selincengiz.onboarding.domain.repo.LocalUserManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataLayerModule {
    @Provides
    fun provideLocalUserManager(
        @ApplicationContext context: Context
    ): LocalUserManager = LocalUserManagerImpl(context)
}