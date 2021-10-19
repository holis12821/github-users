package com.example.githubuserapp.domain.usecase

import com.example.githubuserapp.data.response.ItemsItem
import com.example.githubuserapp.domain.repository.local.LocalDatabaseRepository

class AddFavoriteUsersUseCase(
    private val localDatabaseRepository: LocalDatabaseRepository
) {
    suspend fun execute(entity: ItemsItem) {
        localDatabaseRepository.saveFavoriteUsers(entity = entity)
    }
}