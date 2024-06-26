package com.selincengiz.cart.data.mapper

import com.selincengiz.cart.domain.model.CartUI
import com.selincengiz.cart.domain.model.ProductXUI
import com.selincengiz.core.network.dto.cart.CartResponse
import com.selincengiz.core.network.dto.cart.ProductX

fun CartResponse.mapToCartUI(): CartUI {
    return CartUI(
        discountedTotal = discountedTotal,
        id = id,
        products = products.map { it.mapToProductXUI() },
        total = total,
        totalProducts = totalProducts,
        totalQuantity = totalQuantity,
        userId = userId
    )
}

fun ProductX.mapToProductXUI(): ProductXUI {
    return ProductXUI(
        discountPercentage = discountPercentage,
        discountedPrice = discountedPrice,
        id = id,
        price = price,
        quantity = quantity,
        thumbnail = thumbnail,
        title = title,
        total = total
    )
}