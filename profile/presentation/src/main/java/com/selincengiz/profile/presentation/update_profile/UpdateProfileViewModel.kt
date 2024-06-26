package com.selincengiz.profile.presentation.update_profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selincengiz.core.common.UiEvent
import com.selincengiz.profile.domain.model.UserUI
import com.selincengiz.profile.domain.usecase.ProfileUseCase
import com.selincengiz.profile.presentation.get_profile.ProfileState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UpdateProfileViewModel @Inject constructor(
    private val profileUseCase: ProfileUseCase
) : ViewModel() {
    private var _state = MutableStateFlow<UpdateProfileState>(UpdateProfileState.Entry)
    val state: StateFlow<UpdateProfileState>
        get() = _state.asStateFlow()

    fun updateProfile(userId: Int, user: UserUI) {
        profileUseCase.updateProfile(userId, user).onEach {
            when (it) {
                is UiEvent.Loading -> {
                    _state.value = UpdateProfileState.Loading
                }

                is UiEvent.Error -> {
                    _state.value = UpdateProfileState.Error(it.message)
                }

                is UiEvent.Success -> {
                    _state.value = UpdateProfileState.Data(it.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}