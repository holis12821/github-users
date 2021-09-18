package com.example.githubuserapp.domain.usecase

import com.example.githubuserapp.data.response.UsersResponse
import com.example.githubuserapp.domain.repository.GithubUsersRepository
import com.example.githubuserapp.presentation.ui.activity.MainActivityViewState
import kotlinx.coroutines.flow.Flow

class GithubUsersUseCase(private val repository: GithubUsersRepository) {
    suspend operator fun invoke(
        query: String?
    ): Flow<MainActivityViewState<UsersResponse>> {
        return repository.getSearchUsers(query = query)
    }
}