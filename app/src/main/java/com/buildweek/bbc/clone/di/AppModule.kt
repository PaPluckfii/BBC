package com.buildweek.bbc.clone.di

import com.buildweek.bbc.clone.data.remote.api.OpenSourceApiService
import com.buildweek.bbc.clone.data.remote.api.SpringBootApiService
import com.buildweek.bbc.clone.repository.DefaultMainRepository
import com.buildweek.bbc.clone.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val SPRING_BOOT_BASE_URL = "https://532629708222.ngrok.io/api/bbc/"
private const val OPEN_SOURCE_BASE_URL = "020685a3862d47c383cf4a4506d5f303"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideSpringRepository(apiService: SpringBootApiService) = DefaultMainRepository()

    @Singleton
    @Provides
    fun provideSpringBootApi() : SpringBootApiService = Retrofit.Builder()
        .baseUrl(SPRING_BOOT_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(SpringBootApiService::class.java)

    @Singleton
    @Provides
    fun provideOpenSourceApi() : OpenSourceApiService = Retrofit.Builder()
        .baseUrl(OPEN_SOURCE_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(OpenSourceApiService::class.java)

}