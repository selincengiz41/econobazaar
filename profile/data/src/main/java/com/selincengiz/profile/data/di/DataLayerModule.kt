package com.selincengiz.profile.data.di

import com.selincengiz.core.network.dataproviders.NetworkDataProviders
import com.selincengiz.profile.data.repo.ProfileRepositoryImpl
import com.selincengiz.profile.domain.repo.ProfileRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataLayerModule {

    @Provides
    fun provideProfileRepo(dataProviders: NetworkDataProviders): ProfileRepository =
        ProfileRepositoryImpl(dataProviders = dataProviders)
}