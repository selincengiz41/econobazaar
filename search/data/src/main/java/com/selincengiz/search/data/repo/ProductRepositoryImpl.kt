package com.selincengiz.search.data.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.selincengiz.core.network.dataproviders.NetworkDataProviders
import com.selincengiz.search.data.pager.ProductPagingSource
import com.selincengiz.search.domain.model.ProductUI
import com.selincengiz.search.domain.repo.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val dataProviders: NetworkDataProviders
) : ProductRepository {
    override fun searchProducts(query:String): Flow<PagingData<ProductUI>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                ProductPagingSource(
                    dataProviders = dataProviders,
                    query=query
                )
            }
        ).flow
    }
}