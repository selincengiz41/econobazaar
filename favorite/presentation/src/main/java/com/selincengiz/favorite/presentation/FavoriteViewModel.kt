package com.selincengiz.favorite.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selincengiz.core.common.UiEvent
import com.selincengiz.favorite.domain.usecases.GetFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoritesUseCase: GetFavoritesUseCase
) : ViewModel() {

    private var _state = MutableStateFlow<FavoriteState>(FavoriteState.Entry)
    val state: StateFlow<FavoriteState>
        get() = _state.asStateFlow()

    fun getFavorites() {
        getFavoritesUseCase().onEach {
            when (it) {
                is UiEvent.Loading -> {
                    _state.value = FavoriteState.Loading
                }

                is UiEvent.Error -> {
                    _state.value = FavoriteState.Error(it.message)
                }

                is UiEvent.Success -> {
                    _state.value = FavoriteState.Data(it.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}