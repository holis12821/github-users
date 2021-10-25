package com.example.githubuserapp.domain.usecase

import com.example.githubuserapp.data.response.DetailUsersResponse
import com.example.githubuserapp.domain.repository.local.LocalDatabaseRepository

class AddFavoriteUsersUseCase(
    private val localDatabaseRepository: LocalDatabaseRepository
) {
    suspend fun execute(entity: DetailUsersResponse) {
        localDatabaseRepository.saveFavoriteUsers(entity = entity)
    }
}