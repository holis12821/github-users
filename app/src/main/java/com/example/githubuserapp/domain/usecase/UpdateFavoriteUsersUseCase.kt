package com.example.githubuserapp.domain.usecase

import com.example.githubuserapp.data.response.model.ItemsItem
import com.example.githubuserapp.domain.repository.api.GithubUsersRepository

class UpdateFavoriteUsersUseCase(
    private val localDatabaseRepository: GithubUsersRepository
) {
     suspend fun execute(favUsers: ItemsItem) {
         localDatabaseRepository.updateFavUsersData(favUsers = favUsers)
     }
}