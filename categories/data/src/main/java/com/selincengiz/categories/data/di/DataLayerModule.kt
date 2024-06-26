package com.selincengiz.categories.data.di

import com.selincengiz.categories.data.repo.CategoryRepositoryImpl
import com.selincengiz.categories.domain.repo.CategoryRepository
import com.selincengiz.core.network.dataproviders.NetworkDataProviders
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataLayerModule {
    @Provides
    fun provideCategoryRepo(dataProviders: NetworkDataProviders): CategoryRepository =
        CategoryRepositoryImpl(dataProviders = dataProviders)
}