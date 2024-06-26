package com.selincengiz.cart.data.di

import com.selincengiz.cart.data.repo.CartRepositoryImpl
import com.selincengiz.cart.domain.repo.CartRepository
import com.selincengiz.core.network.dataproviders.NetworkDataProviders
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataLayerModule {
    @Provides
    fun provideCartRepo(dataProviders: NetworkDataProviders): CartRepository =
        CartRepositoryImpl(dataProviders = dataProviders)
}