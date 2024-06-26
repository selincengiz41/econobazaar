package com.selincengiz.detail.domain.di

import com.selincengiz.detail.domain.repo.DetailRepository
import com.selincengiz.detail.domain.usecases.AddCart
import com.selincengiz.detail.domain.usecases.AddFavorite
import com.selincengiz.detail.domain.usecases.DeleteFavorite
import com.selincengiz.detail.domain.usecases.GetDetail
import com.selincengiz.detail.domain.usecases.GetDetailUseCase
import com.selincengiz.detail.domain.usecases.GetFavorites
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainLayerModule {

    @Provides
    fun provideDetailUseCase(
        detailRepository: DetailRepository
    ) = GetDetailUseCase(
        addFavorite = AddFavorite(detailRepository),
        deleteFavorite = DeleteFavorite(detailRepository),
        getDetail = GetDetail(detailRepository),
        getFavorites = GetFavorites(detailRepository),
        addCart = AddCart(detailRepository)
    )
}