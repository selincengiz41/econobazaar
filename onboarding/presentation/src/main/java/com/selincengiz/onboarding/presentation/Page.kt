package com.selincengiz.onboarding.presentation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Page(
    @StringRes val title: Int,
    @StringRes val description: Int,
    @DrawableRes val image: Int
)

val pages = listOf(
    Page(
        title = com.selincengiz.core.common.R.string.onboarding_title1,
        description = com.selincengiz.core.common.R.string.onboarding_desc1,
        image = com.selincengiz.core.common.R.drawable.onboarding1
    ),
    Page(
        title = com.selincengiz.core.common.R.string.onboarding_title2,
        description = com.selincengiz.core.common.R.string.onboarding_desc2,
        image = com.selincengiz.core.common.R.drawable.onboarding2
    ),
    Page(
        title = com.selincengiz.core.common.R.string.onboarding_title3,
        description = com.selincengiz.core.common.R.string.onboarding_desc3,
        image = com.selincengiz.core.common.R.drawable.onboarding3
    )
)