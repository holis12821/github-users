package com.example.githubuserapp.domain.usecase

import com.example.githubuserapp.data.response.DetailUsersResponse
import com.example.githubuserapp.domain.repository.local.LocalDatabaseRepository
import kotlinx.coroutines.flow.Flow

class GetDetailFavoriteUseCase(
    private val localDatabaseRepository: LocalDatabaseRepository
) {
    suspend fun execute(username: String): Flow<DetailUsersResponse> {
      return localDatabaseRepository.getUserDetail(username = username)
    }
}