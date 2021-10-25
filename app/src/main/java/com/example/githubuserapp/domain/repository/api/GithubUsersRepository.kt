/**
 * Github Users Apps
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 17/09/21 03:36 PM
 * Last modified 17/09/21 03:36 PM by Nurholis*/
package com.example.githubuserapp.domain.repository.api

import com.example.githubuserapp.data.response.DetailUsersResponse
import com.example.githubuserapp.data.response.model.ItemsItem
import com.example.githubuserapp.data.response.UsersResponse
import kotlinx.coroutines.flow.Flow

interface GithubUsersRepository {
    suspend fun getSearchUsers(query: String?): Flow<UsersResponse>
    suspend fun getDetailUsers(username: String?): Flow<DetailUsersResponse>
    suspend fun getFollowers(username: String?): Flow<List<ItemsItem>>
    suspend fun getFollowing(username: String?): Flow<List<ItemsItem>>
}