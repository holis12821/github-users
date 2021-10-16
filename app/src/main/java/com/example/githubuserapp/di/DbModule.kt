package com.example.githubuserapp.di

import android.content.Context
import org.koin.dsl.module

/**
 * Db module to handle local database with room and inject database as singleton*/
val dbModule = module {
    single {  }
}

fun provideDatabaseName(): String = "FavoriteUsersDB"

