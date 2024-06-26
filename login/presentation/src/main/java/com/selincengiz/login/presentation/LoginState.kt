package com.selincengiz.login.presentation

sealed interface LoginState {
    data object Entry : LoginState
    data object Success : LoginState
    data class Error(val throwable: String) : LoginState
}