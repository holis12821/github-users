package com.example.githubuserapp.domain.usecase

import com.example.githubuserapp.data.response.ItemsItem
import com.example.githubuserapp.domain.repository.local.LocalDatabaseRepository
import com.example.githubuserapp.presentation.ui.activity.MainViewState
import kotlinx.coroutines.flow.Flow

class GetFavoriteUsersUseCase(
    private val localDatabaseRepository: LocalDatabaseRepository
) {
    suspend fun execute(): Flow<MainViewState<List<ItemsItem>>> {
        return localDatabaseRepository.getAllFavoriteUsers()
    }
}