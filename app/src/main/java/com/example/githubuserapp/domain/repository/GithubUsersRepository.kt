package com.example.githubuserapp.domain.repository

import com.example.githubuserapp.data.response.UsersResponse
import com.example.githubuserapp.presentation.ui.activity.MainActivityViewState
import kotlinx.coroutines.flow.Flow

interface GithubUsersRepository {
    suspend fun getSearchUsers(query: String?): Flow<MainActivityViewState<UsersResponse>>
}