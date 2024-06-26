package com.selincengiz.onboarding.presentation

sealed class OnBoardingEvent {
    data object SaveAppEntry : OnBoardingEvent()
}