package com.selincengiz.profile.presentation.get_profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selincengiz.core.common.UiEvent
import com.selincengiz.profile.domain.usecase.ProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileUseCase: ProfileUseCase
) : ViewModel() {

    private var _state = MutableStateFlow<ProfileState>(ProfileState.Entry)
    val state: StateFlow<ProfileState>
        get() = _state.asStateFlow()

    fun getProfile(userId:Int) {
        profileUseCase.getProfile(userId).onEach {
            when (it) {
                is UiEvent.Loading -> {
                    _state.value = ProfileState.Loading
                }

                is UiEvent.Error -> {
                    _state.value = ProfileState.Error(it.message)
                }

                is UiEvent.Success -> {
                    _state.value = ProfileState.Data(it.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}