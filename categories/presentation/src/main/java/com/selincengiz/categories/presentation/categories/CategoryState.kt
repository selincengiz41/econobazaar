package com.selincengiz.categories.presentation.categories

import com.selincengiz.categories.domain.model.CategoryUI

sealed interface CategoryState {
    data object Entry : CategoryState
    data object Loading : CategoryState
    data class Data(val list: List<CategoryUI>) : CategoryState
    data class Error(val throwable: String) : CategoryState
}