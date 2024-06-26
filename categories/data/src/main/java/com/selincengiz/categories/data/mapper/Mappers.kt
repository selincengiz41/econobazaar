package com.selincengiz.categories.data.mapper

import com.selincengiz.categories.domain.model.CategoryUI
import com.selincengiz.categories.domain.model.ProductUI
import com.selincengiz.core.network.dto.category.CategoryResponseItem
import com.selincengiz.core.network.dto.product.Product

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

fun CategoryResponseItem.mapToCategoryUI(): CategoryUI {
    return CategoryUI(
        name,
        slug,
        url
    )
}