package com.selincengiz.categories.domain.di

import com.selincengiz.categories.domain.repo.CategoryRepository
import com.selincengiz.categories.domain.usecases.CategoryUseCases
import com.selincengiz.categories.domain.usecases.GetCategories
import com.selincengiz.categories.domain.usecases.GetCategoriesProduct
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainLayerModule {

    @Provides
    fun provideCategoryUseCase(
        categoryRepository: CategoryRepository
    ) = CategoryUseCases(
        getCategories = GetCategories(categoryRepository),
        getCategoriesProduct = GetCategoriesProduct(categoryRepository),
    )
}