package com.selincengiz.favorite.data.mappers

import com.selincengiz.core.local.dto.ProductRoom
import com.selincengiz.favorite.domain.model.ProductUI

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