package com.selincengiz.search.domain.repo

import androidx.paging.PagingData
import com.selincengiz.search.domain.model.ProductUI
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun searchProducts(query:String): Flow<PagingData<ProductUI>>
}