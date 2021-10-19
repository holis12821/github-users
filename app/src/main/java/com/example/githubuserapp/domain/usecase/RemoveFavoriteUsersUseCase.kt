package com.example.githubuserapp.domain.usecase

import com.example.githubuserapp.data.response.ItemsItem
import com.example.githubuserapp.domain.repository.local.LocalDatabaseRepository

class RemoveFavoriteUsersUseCase(
    private val localDatabaseRepository: LocalDatabaseRepository
) {
    suspend fun execute(entity: ItemsItem) {
        localDatabaseRepository.deleteFavoriteUsers(entity = entity)
    }
}