package com.selincengiz.categories.data.pager

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.selincengiz.categories.data.mapper.mapToProductUI
import com.selincengiz.categories.domain.model.ProductUI
import com.selincengiz.core.network.dataproviders.NetworkDataProviders


class CategoriesPagingSource(
    private val dataProviders: NetworkDataProviders,
    private val category: String
) :
    PagingSource<Int, ProductUI>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductUI> {
        val currentPage = params.key ?: 1
        return try {
            val response =
                dataProviders.getCategoriesProducts(category, currentPage, params.loadSize)
            val items = response.products.map { item -> item.mapToProductUI() }

            LoadResult.Page(
                data = items,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (items.isEmpty()) null else currentPage + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ProductUI>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}