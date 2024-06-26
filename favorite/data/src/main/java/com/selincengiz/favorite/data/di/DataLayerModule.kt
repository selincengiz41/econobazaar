package com.selincengiz.favorite.data.di

import com.selincengiz.core.local.dataproviders.LocalDataProviders
import com.selincengiz.favorite.data.repo.FavoriteRepositoryImpl
import com.selincengiz.favorite.domain.repo.FavoriteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataLayerModule {

    @Provides
    fun provideFavoriteRepo(
        localProviders: LocalDataProviders
    ): FavoriteRepository = FavoriteRepositoryImpl(localProviders = localProviders)
}