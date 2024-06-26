package com.selincengiz.detail.data.mapper

import com.selincengiz.core.local.dto.ProductRoom
import com.selincengiz.core.network.dto.cart.CartRequest
import com.selincengiz.core.network.dto.product.Product
import com.selincengiz.detail.domain.model.CartUI
import com.selincengiz.detail.domain.model.ProductCartUI
import com.selincengiz.detail.domain.model.ProductUI

fun Product.mapToProductUI(): ProductUI {
    return ProductUI(
        availabilityStatus,
        brand,
        category,
        description,
        discountPercentage,
        id,
        images,
        minimumOrderQuantity,
        price,
        rating,
        returnPolicy,
        shippingInformation,
        sku,
        stock,
        tags,
        thumbnail,
        title,
        warrantyInformation,
        weight,
    )
}

fun ProductUI.mapToProductRoom(): ProductRoom {
    return ProductRoom(
        availabilityStatus,
        brand,
        category,
        description,
        discountPercentage,
        id,
        images,
        minimumOrderQuantity,
        price,
        rating,
        returnPolicy,
        shippingInformation,
        sku,
        stock,
        tags,
        thumbnail,
        title,
        warrantyInformation,
        weight,
    )
}

fun ProductRoom.mapToProductUI(): ProductUI {
    return ProductUI(
        availabilityStatus,
        brand,
        category,
        description,
        discountPercentage,
        id,
        images,
        minimumOrderQuantity,
        price,
        rating,
        returnPolicy,
        shippingInformation,
        sku,
        stock,
        tags,
        thumbnail,
        title,
        warrantyInformation,
        weight,
    )
}

fun CartUI.mapToCartRequest(): CartRequest {
    return CartRequest(
        products.map { it.mapToProduct() },
        userId
    )
}

fun ProductCartUI.mapToProduct(): com.selincengiz.core.network.dto.cart.Product {
    return com.selincengiz.core.network.dto.cart.Product(
        id,
        quantity
    )
}