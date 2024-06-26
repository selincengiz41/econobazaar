package com.selincengiz.home.data.di

import com.selincengiz.core.network.dataproviders.NetworkDataProviders
import com.selincengiz.home.data.repo.ProductRepositoryImpl
import com.selincengiz.home.domain.repo.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataLayerModule {

    @Provides
    fun provideProductRepo(dataProviders: NetworkDataProviders): ProductRepository =
        ProductRepositoryImpl(dataProviders = dataProviders)
}