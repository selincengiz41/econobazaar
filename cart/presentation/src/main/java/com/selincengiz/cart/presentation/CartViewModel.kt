package com.selincengiz.cart.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selincengiz.cart.domain.usecases.GetCartUseCase
import com.selincengiz.core.common.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getCartUseCase: GetCartUseCase
) : ViewModel() {

    private var _state = MutableStateFlow<CartState>(CartState.Entry)
    val state: StateFlow<CartState>
        get() = _state.asStateFlow()

    fun getCarts(userId: Int) {
        getCartUseCase(userId).onEach {
            when (it) {
                is UiEvent.Loading -> {
                    _state.value = CartState.Loading
                }

                is UiEvent.Error -> {
                    _state.value = CartState.Error(it.message)
                }

                is UiEvent.Success -> {
                    _state.value = CartState.Data(it.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}