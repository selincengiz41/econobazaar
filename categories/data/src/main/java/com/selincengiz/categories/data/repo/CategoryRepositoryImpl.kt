package com.selincengiz.categories.data.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.selincengiz.categories.data.mapper.mapToCategoryUI
import com.selincengiz.categories.data.pager.CategoriesPagingSource
import com.selincengiz.categories.domain.model.CategoryUI
import com.selincengiz.categories.domain.model.ProductUI
import com.selincengiz.categories.domain.repo.CategoryRepository
import com.selincengiz.core.network.dataproviders.NetworkDataProviders
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val dataProviders: NetworkDataProviders
) : CategoryRepository {

    override suspend fun getCategories(): List<CategoryUI> {
        return dataProviders.getCategories().map { item ->
            item.mapToCategoryUI()
        }
    }

    override fun getCategoriesProduct(category: String): Flow<PagingData<ProductUI>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                CategoriesPagingSource(
                    dataProviders = dataProviders,
                    category
                )
            }
        ).flow
    }
}