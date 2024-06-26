package com.selincengiz.search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selincengiz.core.common.UiEvent
import com.selincengiz.search.domain.usecases.SearchProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchProductsUseCase: SearchProductUseCase
) : ViewModel() {

    private var _state = MutableStateFlow<SearchState>(SearchState.Entry)
    val state: StateFlow<SearchState>
        get() = _state.asStateFlow()

    fun searchProducts(query: String) {
        searchProductsUseCase(query).onEach {
            when (it) {
                is UiEvent.Loading -> {
                    _state.value = SearchState.Loading
                }

                is UiEvent.Error -> {
                    _state.value = SearchState.Error(it.message)
                }

                is UiEvent.Success -> {
                    _state.value = SearchState.Data(it.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}