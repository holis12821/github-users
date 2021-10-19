/**
 * Github Users Apps
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 20/09/21 12:37 PM
 * Last modified 17/09/21 12:37 PM by Nurholis*/
package com.example.githubuserapp.domain.usecase

import com.example.githubuserapp.data.response.ItemsItem
import com.example.githubuserapp.domain.repository.api.GithubUsersRepository
import com.example.githubuserapp.presentation.ui.activity.MainViewState
import kotlinx.coroutines.flow.Flow

class FollowingUseCase(private val repository: GithubUsersRepository) {
    suspend fun execute(username: String?): Flow<MainViewState<List<ItemsItem>>> {
        return repository.getFollowing(username = username)
    }
}