package com.selincengiz.categories.domain.repo

import androidx.paging.PagingData
import com.selincengiz.categories.domain.model.CategoryUI
import com.selincengiz.categories.domain.model.ProductUI
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun getCategories(): List<CategoryUI>
    fun getCategoriesProduct(category:String): Flow<PagingData<ProductUI>>
}