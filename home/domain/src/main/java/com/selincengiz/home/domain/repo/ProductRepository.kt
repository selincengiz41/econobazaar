package com.selincengiz.home.domain.repo

import androidx.paging.PagingData
import com.selincengiz.home.domain.model.ProductUI
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
     fun getProducts(): Flow<PagingData<ProductUI>>
}