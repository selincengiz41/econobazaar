package com.selincengiz.login.presentation


import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val auth: FirebaseAuth
) : ViewModel() {

    private val _state = MutableStateFlow<LoginState>(LoginState.Entry)
    val state: StateFlow<LoginState> = _state

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.Logined -> {
                auth.signInWithEmailAndPassword(event.name, event.password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        _state.value = LoginState.Success
                    }
                }
            }
        }
    }
}