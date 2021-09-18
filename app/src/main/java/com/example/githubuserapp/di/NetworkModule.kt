/**
 * Github Users Apps
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 17/09/21 03:57 PM
 * Last modified 17/09/21 03:57 PM by Nurholis*/
package com.example.githubuserapp.di

import com.example.githubuserapp.BuildConfig
import com.example.githubuserapp.external.constant.networkConnectTimeOut
import com.example.githubuserapp.external.constant.networkReadTimeOut
import com.example.githubuserapp.external.constant.networkWriteTimeout
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { provideHttpLoggingInterceptor() }
    single { provideOkHttpClient(get()) }
    single { provideConverterFactory() }
    single {
        val baseUrl = BuildConfig.BASE_URL
        provideRetrofitInstance(get(), get(), baseUrl = baseUrl)
    }
}

//add function to provide dependency with koin module.
fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    return interceptor
}

fun provideOkHttpClient(
    loggingInterceptor: HttpLoggingInterceptor
): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(networkConnectTimeOut, TimeUnit.SECONDS)
        .writeTimeout(networkWriteTimeout, TimeUnit.SECONDS)
        .readTimeout(networkReadTimeOut, TimeUnit.SECONDS)
        .addInterceptor(loggingInterceptor)
        .build()
}

fun provideConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

fun provideRetrofitInstance(
    okHttpClient: OkHttpClient,
    gsonConverterFactory: GsonConverterFactory,
    baseUrl: String
): Retrofit = Retrofit.Builder()
    .baseUrl(baseUrl)
    .client(okHttpClient)
    .addConverterFactory(gsonConverterFactory)
    .build()
