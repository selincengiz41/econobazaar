package com.selincengiz.core.local.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products_favorite")
data class ProductRoom(
    @ColumnInfo("availabilityStatus")
    val availabilityStatus: String,
    @ColumnInfo("brand")
    val brand: String?,
    @ColumnInfo("category")
    val category: String,
    @ColumnInfo("description")
    val description: String,
    @ColumnInfo("discountPercentage")
    val discountPercentage: Double,
    @PrimaryKey
    @ColumnInfo("id")
    val id: Int,
    @ColumnInfo("images")
    val images: List<String>,
    @ColumnInfo("minimumOrderQuantity")
    val minimumOrderQuantity: Int,
    @ColumnInfo("price")
    val price: Double,
    @ColumnInfo("rating")
    val rating: Double,
    @ColumnInfo("returnPolicy")
    val returnPolicy: String,
    @ColumnInfo("shippingInformation")
    val shippingInformation: String,
    @ColumnInfo("sku")
    val sku: String,
    @ColumnInfo("stock")
    val stock: Int,
    @ColumnInfo("tags")
    val tags: List<String>,
    @ColumnInfo("thumbnail")
    val thumbnail: String,
    @ColumnInfo("title")
    val title: String,
    @ColumnInfo("warrantyInformation")
    val warrantyInformation: String,
    @ColumnInfo("weight")
    val weight: Int,
)
