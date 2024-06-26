package com.selincengiz.search.presentation

import androidx.paging.PagingData
import com.selincengiz.search.domain.model.ProductUI
import kotlinx.coroutines.flow.Flow

sealed interface SearchState{
    data object Entry : SearchState
    data object Loading : SearchState
    data class Data(val list: Flow<PagingData<ProductUI>>) : SearchState
    data class Error(val throwable: String) : SearchState
}