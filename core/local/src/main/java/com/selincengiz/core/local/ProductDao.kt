package com.selincengiz.core.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.selincengiz.core.local.dto.ProductRoom

@Dao
interface ProductDao {
    @Query("SELECT * FROM products_favorite ")
    suspend fun getFavorites(): List<ProductRoom>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorites(product: ProductRoom)

    @Delete
    suspend fun deleteFavorites(product: ProductRoom)

}