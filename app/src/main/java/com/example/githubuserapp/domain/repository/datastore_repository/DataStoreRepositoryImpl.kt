package com.example.githubuserapp.domain.repository.datastore_repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreRepositoryImpl(
   private val dataStore: DataStore<Preferences>,
   private val themeSettings: Preferences.Key<Boolean>
): DataStoreRepository {

    override fun getThemeSettings(): Flow<Boolean> {
       return dataStore.data.map { preferences ->
           preferences[themeSettings] ?: false
       }
    }

    override suspend fun saveThemeSetting(isDarkModeActive: Boolean) {
        dataStore.edit { preferences ->
            preferences[themeSettings] = isDarkModeActive
        }
    }
}