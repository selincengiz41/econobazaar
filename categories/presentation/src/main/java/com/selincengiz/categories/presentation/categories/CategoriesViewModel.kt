package com.selincengiz.categories.presentation.categories

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
class CategoriesViewModel @Inject constructor(
    private val categoryUseCases: CategoryUseCases
) : ViewModel() {
    private var _state = MutableStateFlow<CategoryState>(CategoryState.Entry)
    val state: StateFlow<CategoryState>
        get() = _state.asStateFlow()

    fun getCategories() {
        categoryUseCases.getCategories().onEach {
            when (it) {
                is UiEvent.Loading -> {
                    _state.value = CategoryState.Loading
                }

                is UiEvent.Error -> {
                    _state.value = CategoryState.Error(it.message)
                }

                is UiEvent.Success -> {
                    _state.value = CategoryState.Data(it.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}