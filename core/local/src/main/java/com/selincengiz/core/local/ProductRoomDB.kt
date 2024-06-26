package com.selincengiz.core.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.selincengiz.core.local.dto.ProductRoom

@Database(entities = [ProductRoom::class], version = 1)
@androidx.room.TypeConverters(value = [TypeConverters::class])
abstract class ProductRoomDB : RoomDatabase() {
    abstract fun productDao(): ProductDao
}