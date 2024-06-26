package com.selincengiz.home.data.mapper

import com.selincengiz.core.network.dto.product.Product
import com.selincengiz.home.domain.model.ProductUI


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

