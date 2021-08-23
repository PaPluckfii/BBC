package com.buildweek.bbc.clone.di

import com.buildweek.bbc.clone.data.remote.api.OpenSourceApiService
import com.buildweek.bbc.clone.data.remote.api.SpringBootApiService
import com.buildweek.bbc.clone.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val SPRING_BOOT_BASE_URL = "https://532629708222.ngrok.io/api/bbc/"
private const val OPEN_SOURCE_BASE_URL = "https://newsapi.org/"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        springBootApiService: SpringBootApiService,
        openSourceApiService: OpenSourceApiService
    ) = MainRepository(springBootApiService,openSourceApiService)

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