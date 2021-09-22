/**
 * Github Users Apps
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 17/09/21 03:36 PM
 * Last modified 17/09/21 03:36 PM by Nurholis*/
package com.example.githubuserapp.domain.repository

import com.example.githubuserapp.data.response.DetailUsersResponse
import com.example.githubuserapp.data.response.ItemsItem
import com.example.githubuserapp.data.response.UsersResponse
import com.example.githubuserapp.presentation.ui.activity.MainActivityViewState
import kotlinx.coroutines.flow.Flow

interface GithubUsersRepository {
    suspend fun getSearchUsers(query: String?): Flow<MainActivityViewState<UsersResponse>>
    suspend fun getDetailUsers(username: String?): Flow<MainActivityViewState<DetailUsersResponse>>
    suspend fun getFollowers(username: String?): Flow<MainActivityViewState<List<ItemsItem>>>
    suspend fun getFollowing(username: String?): Flow<MainActivityViewState<List<ItemsItem>>>
}