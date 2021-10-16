package com.example.githubuserapp.di

import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStoreFile
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val preferencesModule = module {
    //create each instance
    factory(named("theme_setting")) {
        booleanPreferencesKey("theme_setting")
    }

    single {
        PreferenceDataStoreFactory.create {
            androidContext().preferencesDataStoreFile("my_settings_preferences")
        }
    }
}