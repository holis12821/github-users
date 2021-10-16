package com.example.githubuserapp.domain.usecase

import com.example.githubuserapp.domain.repository.datastore_repository.DataStoreRepository

class SaveThemeDataStoreUseCase(private val dataStoreRepository: DataStoreRepository) {
    suspend fun execute(isDarkModeActive: Boolean) {
        return dataStoreRepository.saveThemeSetting(isDarkModeActive)
    }
}