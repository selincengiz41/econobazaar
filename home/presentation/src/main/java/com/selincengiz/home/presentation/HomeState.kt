package com.selincengiz.home.presentation

import androidx.paging.PagingData
import com.selincengiz.home.domain.model.ProductUI
import kotlinx.coroutines.flow.Flow

sealed interface HomeState{
    data object Entry : HomeState
    data object Loading : HomeState
    data class Data(val list: Flow<PagingData<ProductUI>>) : HomeState
    data class Error(val throwable: String) : HomeState
}