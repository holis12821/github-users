package com.example.githubuserapp.domain.usecase

import com.example.githubuserapp.domain.repository.local.DataStoreRepository

class AddThemeDataStoreUseCase(private val dataStoreRepository: DataStoreRepository) {
    suspend fun execute(isDarkModeActive: Boolean) {
        return dataStoreRepository.saveThemeSetting(isDarkModeActive)
    }
}