package com.selincengiz.favorite.domain.di

import com.selincengiz.favorite.domain.repo.FavoriteRepository
import com.selincengiz.favorite.domain.usecases.GetFavoritesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainLayerModule {

    @Provides
    fun provideDetailUseCase(
        favoriteRepository: FavoriteRepository
    ) = GetFavoritesUseCase(
        favoriteRepository = favoriteRepository
    )
}