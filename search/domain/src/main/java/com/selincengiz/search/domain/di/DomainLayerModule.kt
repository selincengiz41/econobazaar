package com.selincengiz.search.domain.di

import com.selincengiz.search.domain.repo.ProductRepository
import com.selincengiz.search.domain.usecases.SearchProductUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainLayerModule {

    @Provides
    fun provideGetMovieListUseCase(productRepository: ProductRepository): SearchProductUseCase =
        SearchProductUseCase(productRepository = productRepository)
}