package com.example.githubuserapp.domain.usecase

import com.example.githubuserapp.data.response.model.ItemsItem
import com.example.githubuserapp.domain.repository.api.GithubUsersRepository
import com.example.githubuserapp.domain.repository.local.LocalDatabaseRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteListUsersUseCase(
    private val localDatabaseRepository: LocalDatabaseRepository
) {
   suspend fun execute(): Flow<List<ItemsItem>>  = localDatabaseRepository.favoriteUsers()
}