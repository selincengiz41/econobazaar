package com.selincengiz.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selincengiz.core.common.UiEvent
import com.selincengiz.home.domain.usecases.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
) : ViewModel() {

    private var _state = MutableStateFlow<HomeState>(HomeState.Entry)
    val state: StateFlow<HomeState>
        get() = _state.asStateFlow()

    fun getProducts() {
        getProductsUseCase().onEach {
            when (it) {
                is UiEvent.Loading -> {
                    _state.value = HomeState.Loading
                    delay(1000)
                }

                is UiEvent.Error -> {
                    _state.value = HomeState.Error(it.message)
                }

                is UiEvent.Success -> {
                    _state.value = HomeState.Data(it.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}