/**
 * Github Users Apps
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 17/09/21 03:36 PM
 * Last modified 17/09/21 03:36 PM by Nurholis*/
package com.example.githubuserapp.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.githubuserapp.data.local.db.LocalDb
import com.example.githubuserapp.data.network.GithubUsersDataSource
import com.example.githubuserapp.domain.repository.api.GithubUsersRepository
import com.example.githubuserapp.domain.repository.api.GithubUsersRepositoryImpl
import com.example.githubuserapp.domain.repository.local.DataStoreRepository
import com.example.githubuserapp.domain.repository.local.DataStoreRepositoryImpl
import com.example.githubuserapp.domain.repository.local.LocalDatabaseRepository
import com.example.githubuserapp.domain.repository.local.LocalDatabaseRepositoryImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val repositoryModule = module {
    single { provideGithubUsersDataSource(get()) }
    single { provideGithubUsersRepository(get()) }
    single { provideDataStorePreferences(dataStore = get(), themeSettings = get(named("theme_setting"))) }
    single { provideDatabaseRepository(get()) }
}

fun provideGithubUsersDataSource(retrofit: Retrofit): GithubUsersDataSource {
    return retrofit.create(GithubUsersDataSource::class.java)
}

fun provideGithubUsersRepository(dataSource: GithubUsersDataSource): GithubUsersRepository {
    return GithubUsersRepositoryImpl(dataSource)
}

fun provideDataStorePreferences(dataStore: DataStore<Preferences>, themeSettings: Preferences.Key<Boolean>): DataStoreRepository {
    return DataStoreRepositoryImpl(dataStore = dataStore, themeSettings = themeSettings)
}

fun provideDatabaseRepository(localDb: LocalDb): LocalDatabaseRepository {
    return LocalDatabaseRepositoryImpl(localDb.favoriteDao())
}