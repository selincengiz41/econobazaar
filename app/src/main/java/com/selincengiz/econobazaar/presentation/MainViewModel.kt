package com.selincengiz.econobazaar.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.selincengiz.econobazaar.R
import com.selincengiz.onboarding.domain.usecase.AppEntryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appEntryUseCase: AppEntryUseCase,
    private val auth: FirebaseAuth
) : ViewModel() {

    var startDestination =
        MutableStateFlow(R.id.splashFragment)
        private set

    init {
        appEntryUseCase.readAppEntry().onEach { shouldStartFromHomeScreen ->
            Log.i("start", shouldStartFromHomeScreen.toString())
            startDestination.value = if (shouldStartFromHomeScreen) {
                auth.currentUser?.let {
                    com.selincengiz.home.presentation.R.id.home_nav_graph

                } ?: com.selincengiz.login.presentation.R.id.login_nav
            } else {
                com.selincengiz.onboarding.presentation.R.id.onboarding_nav
            }
        }.launchIn(viewModelScope)
    }

    fun signOut() {
        auth.signOut()
    }
}