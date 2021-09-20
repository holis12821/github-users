/**
 * Github Users Apps
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 17/09/21 03:36 PM
 * Last modified 17/09/21 03:36 PM by Nurholis*/
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