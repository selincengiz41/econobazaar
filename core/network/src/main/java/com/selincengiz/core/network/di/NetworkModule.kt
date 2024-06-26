package com.selincengiz.core.network.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.selincengiz.core.common.Constants.BASE_URL
import com.selincengiz.core.common.Constants.TIMEOUT
import com.selincengiz.core.network.ApiService
import com.selincengiz.core.network.dataproviders.NetworkDataProviders
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideChuckerInterceptor(@ApplicationContext context: Context) =
        ChuckerInterceptor.Builder(context).build()

    @Provides
    fun provideOkHttpClient(chucker: ChuckerInterceptor) = OkHttpClient.Builder().apply {
        addInterceptor(chucker)
        readTimeout(TIMEOUT, TimeUnit.SECONDS)
        connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        writeTimeout(TIMEOUT, TimeUnit.SECONDS)
    }.build()

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideApiService(retrofit: Retrofit) = retrofit.create<ApiService>()

    @Provides
    fun provideNetworkDataProviders(apiService: ApiService) =
        NetworkDataProviders(apiService = apiService)
}