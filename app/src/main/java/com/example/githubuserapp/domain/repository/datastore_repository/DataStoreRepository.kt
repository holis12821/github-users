package com.example.githubuserapp.domain.repository.datastore_repository

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    fun getThemeSettings(): Flow<Boolean>
    suspend fun saveThemeSetting(isDarkModeActive: Boolean)
}