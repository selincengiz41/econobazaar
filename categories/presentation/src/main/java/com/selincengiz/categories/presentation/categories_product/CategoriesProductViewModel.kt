package com.selincengiz.categories.presentation.categories_product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selincengiz.categories.domain.usecases.CategoryUseCases
import com.selincengiz.core.common.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CategoriesProductViewModel @Inject constructor(
    private val categoryUseCases: CategoryUseCases
) : ViewModel() {
    private var _state = MutableStateFlow<CategoryProductState>(CategoryProductState.Entry)
    val state: StateFlow<CategoryProductState>
        get() = _state.asStateFlow()

    fun getCategoriesProduct(category: String) {
        categoryUseCases.getCategoriesProduct(category).onEach {
            when (it) {
                is UiEvent.Loading -> {
                    _state.value = CategoryProductState.Loading
                }

                is UiEvent.Error -> {
                    _state.value = CategoryProductState.Error(it.message)
                }

                is UiEvent.Success -> {
                    _state.value = CategoryProductState.Data(it.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}