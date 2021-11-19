/**
 * Github Users Apps
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 20/09/21 12:37 PM
 * Last modified 17/09/21 12:37 PM by Nurholis*/
package com.example.githubuserapp.domain.usecase

import com.example.githubuserapp.data.response.model.ItemsItem
import com.example.githubuserapp.domain.repository.api.GithubUsersRepository
import kotlinx.coroutines.flow.Flow

class DetailUsersUseCase(private val repository: GithubUsersRepository) {
    suspend fun execute(username: String?): Flow<ItemsItem> {
        return repository.getDetailUsers(username = username)
    }


}