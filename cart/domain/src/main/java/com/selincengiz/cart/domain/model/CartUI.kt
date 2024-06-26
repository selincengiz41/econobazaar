package com.selincengiz.cart.domain.model

data class CartUI(
    val discountedTotal: Double,
    val id: Int,
    val products: List<ProductXUI>,
    val total: Double,
    val totalProducts: Int,
    val totalQuantity: Int,
    val userId: Int
)
