package com.example.githubuserapp.domain.usecase

import com.example.githubuserapp.data.response.DetailUsersResponse
import com.example.githubuserapp.domain.repository.local.LocalDatabaseRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteUsersUseCase(
    private val localDatabaseRepository: LocalDatabaseRepository
) {
    suspend fun execute(): Flow<List<DetailUsersResponse>> {
        return localDatabaseRepository.getAllFavoriteUsers()
    }
}