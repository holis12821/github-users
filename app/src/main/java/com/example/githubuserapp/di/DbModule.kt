package com.example.githubuserapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.githubuserapp.data.local.LocalDb
import com.example.githubuserapp.data.local.dao.FavoriteDao

import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dbModule = module {
    factory {
        get<LocalDb>().favoriteDao()
    }
    single { provideDatabase(androidContext(), provideDatabaseName()) }
}

fun provideDatabaseName(): String = "FavoriteUserDB"

fun provideDatabase(context: Context, databaseName: String): LocalDb {
    return Room.databaseBuilder(context, LocalDb::class.java, databaseName)
        .build()
}


