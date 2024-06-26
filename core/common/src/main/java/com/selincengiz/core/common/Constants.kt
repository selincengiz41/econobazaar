package com.selincengiz.core.common

object Constants {
    const val BASE_URL = "https://dummyjson.com/"
    const val GET_PRODUCTS = "products"
    const val GET_PRODUCT_DETAIL = "products/{productId}"
    const val SEARCH_PRODUCTS = "products/search"
    const val GET_CATEGORIES = "products/categories"
    const val GET_CATEGORIES_PRODUCTS = "products/category/{category}"
    const val ADD_TO_CART = "carts/add"
    const val GET_CART = "carts/{userId}"
    const val GET_PROFILE = "users/{userId}"
    const val UPDATE_PROFILE = "users/{userId}"
    const val USER_SETTINGS = "userSettings"
    const val APP_ENTRY = "appEntry"
    const val TIMEOUT = 60L
}