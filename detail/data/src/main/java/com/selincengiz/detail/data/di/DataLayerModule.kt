package com.selincengiz.detail.data.di

import com.selincengiz.core.local.dataproviders.LocalDataProviders
import com.selincengiz.core.network.dataproviders.NetworkDataProviders
import com.selincengiz.detail.data.repo.DetailRepositoryImpl
import com.selincengiz.detail.domain.repo.DetailRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataLayerModule {
    @Provides
    fun provideDetailRepo(
        dataProviders: NetworkDataProviders,
        localProviders: LocalDataProviders
    ): DetailRepository =
        DetailRepositoryImpl(dataProviders = dataProviders, localProviders = localProviders)
}