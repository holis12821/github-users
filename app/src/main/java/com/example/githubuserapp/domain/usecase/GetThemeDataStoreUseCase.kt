package com.example.githubuserapp.domain.usecase

import com.example.githubuserapp.domain.repository.local.DataStoreRepository
import kotlinx.coroutines.flow.Flow

class GetThemeDataStoreUseCase(private val dataStoreRepository: DataStoreRepository) {
    fun execute(): Flow<Boolean> {
        return dataStoreRepository.getThemeSettings()
    }
}