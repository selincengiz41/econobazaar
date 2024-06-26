package com.selincengiz.home.domain.di

import com.selincengiz.home.domain.repo.ProductRepository
import com.selincengiz.home.domain.usecases.GetProductsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainLayerModule {

    @Provides
    fun provideGetMovieListUseCase(productRepository: ProductRepository): GetProductsUseCase =
        GetProductsUseCase(productRepository = productRepository)

}