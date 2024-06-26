package com.selincengiz.detail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selincengiz.core.common.UiEvent
import com.selincengiz.detail.domain.model.CartUI
import com.selincengiz.detail.domain.model.ProductUI
import com.selincengiz.detail.domain.usecases.GetDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val detailUseCase: GetDetailUseCase
) : ViewModel() {
    private var _state = MutableStateFlow<DetailState>(DetailState.Entry)
    val state: StateFlow<DetailState>
        get() = _state.asStateFlow()

    fun getDetail(productId: Int) {
        detailUseCase.getDetail(productId).onEach {
            when (it) {
                is UiEvent.Loading -> {
                    _state.value = DetailState.Loading
                }

                is UiEvent.Error -> {
                    _state.value = DetailState.Error(it.message)
                }

                is UiEvent.Success -> {
                    _state.value = DetailState.Data(it.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun addCart(cart: CartUI) {
        detailUseCase.addCart(cart).onEach {
            when (it) {
                is UiEvent.Loading -> {}

                is UiEvent.Error -> {
                    _state.value = DetailState.Error(it.message)
                }

                is UiEvent.Success -> {
                    _state.value = DetailState.AddCart("Added to cart")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun addFavorite(productUI: ProductUI) {
        detailUseCase.addFavorite(productUI).onEach {
            when (it) {
                is UiEvent.Loading -> {}

                is UiEvent.Error -> {
                    _state.value = DetailState.Error(it.message)
                }

                is UiEvent.Success -> {
                    _state.value = DetailState.IsFavorite(true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun deleteFavorite(productUI: ProductUI) {
        detailUseCase.deleteFavorite(productUI).onEach {
            when (it) {
                is UiEvent.Loading -> {}

                is UiEvent.Error -> {
                    _state.value = DetailState.Error(it.message)
                }

                is UiEvent.Success -> {
                    _state.value = DetailState.IsFavorite(false)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun isFavorite(id: Int) {
        detailUseCase.getFavorites().onEach {
            when (it) {
                is UiEvent.Loading -> {}

                is UiEvent.Error -> {
                    _state.value = DetailState.Error(it.message)
                }

                is UiEvent.Success -> {
                    val res = it.data.find {
                        it.id == id
                    }
                    if (res == null) {
                        _state.value = DetailState.IsFavorite(false)
                    } else {
                        _state.value = DetailState.IsFavorite(true)
                    }

                }
            }
        }.launchIn(viewModelScope)
    }
}