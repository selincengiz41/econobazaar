package com.selincengiz.core.local.di

import android.content.Context
import androidx.room.Room
import com.selincengiz.core.local.ProductDao
import com.selincengiz.core.local.ProductRoomDB
import com.selincengiz.core.local.dataproviders.LocalDataProviders
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    fun provideRoomDB(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, ProductRoomDB::class.java, "products_favorite_db").build()

    @Provides
    fun provideRoomDao(roomDB: ProductRoomDB) = roomDB.productDao()

    @Provides
    fun provideLocalDataProviders(productDao: ProductDao) =
        LocalDataProviders(productDao = productDao)
}