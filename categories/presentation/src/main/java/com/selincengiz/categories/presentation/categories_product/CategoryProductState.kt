package com.selincengiz.categories.presentation.categories_product

import androidx.paging.PagingData
import com.selincengiz.categories.domain.model.ProductUI
import kotlinx.coroutines.flow.Flow

sealed interface CategoryProductState {
    data object Entry : CategoryProductState
    data object Loading : CategoryProductState
    data class Data(val list: Flow<PagingData<ProductUI>>) : CategoryProductState
    data class Error(val throwable: String) : CategoryProductState
}