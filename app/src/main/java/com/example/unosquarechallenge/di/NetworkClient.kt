package com.example.unosquarechallenge.di

import com.example.unosquarechallenge.Const.Companion.BASE_URL
import com.example.unosquarechallenge.network.PostServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkClient {

    @Provides
    @Singleton
    fun getServices(): PostServices {

        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()

        return Retrofit.Builder().apply {
            baseUrl(BASE_URL)
            client(client)
            addConverterFactory(GsonConverterFactory.create())
        }.build().create(PostServices::class.java)
    }
}