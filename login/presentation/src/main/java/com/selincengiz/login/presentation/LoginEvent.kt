package com.selincengiz.login.presentation

sealed class LoginEvent {
    data class Logined(val name: String, val password: String) : LoginEvent()
}