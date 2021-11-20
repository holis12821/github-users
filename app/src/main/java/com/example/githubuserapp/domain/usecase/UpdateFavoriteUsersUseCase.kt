package com.example.githubuserapp.domain.usecase

import com.example.githubuserapp.data.response.model.ItemsItem
import com.example.githubuserapp.domain.repository.api.GithubUsersRepository
import com.example.githubuserapp.domain.repository.local.LocalDatabaseRepository

class UpdateFavoriteUsersUseCase(
    private val localDatabaseRepository: LocalDatabaseRepository
) {
     suspend fun execute(favUsers: ItemsItem) {
         localDatabaseRepository.updateFavUsersData(favUsers = favUsers)
     }
}