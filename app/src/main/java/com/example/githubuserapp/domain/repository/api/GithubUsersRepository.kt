/**
 * Github Users Apps
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 17/09/21 03:36 PM
 * Last modified 17/09/21 03:36 PM by Nurholis*/
package com.example.githubuserapp.domain.repository.api

import com.example.githubuserapp.data.response.model.ItemsItem
import com.example.githubuserapp.data.response.UsersResponse
import kotlinx.coroutines.flow.Flow

interface GithubUsersRepository {
    //remote repository
    suspend fun getSearchUsers(query: String?): Flow<UsersResponse>
    suspend fun getDetailUsers(username: String?): Flow<ItemsItem>
    suspend fun getFollowers(username: String?): Flow<List<ItemsItem>>
    suspend fun getFollowing(username: String?): Flow<List<ItemsItem>>

    //local database repository
    suspend fun insertFavUsersData(favUsers: ItemsItem?)
    suspend fun updateFavUsersData(favUsers: ItemsItem?)
    suspend fun deleteFavUsersData(favUsers: ItemsItem?)
    suspend fun favoriteUsers(): Flow<List<ItemsItem>>
}