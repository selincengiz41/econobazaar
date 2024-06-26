package com.selincengiz.cart.domain.di

import com.selincengiz.cart.domain.repo.CartRepository
import com.selincengiz.cart.domain.usecases.GetCartUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainLayerModule {
    @Provides
    fun provideGetMovieListUseCase(cartRepository: CartRepository): GetCartUseCase =
        GetCartUseCase(cartRepository = cartRepository)
}