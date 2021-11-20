/**
 * Github Users Apps
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 17/09/21 03:36 PM
 * Last modified 17/09/21 03:36 PM by Nurholis*/
package com.example.githubuserapp.domain.repository.api

import com.example.githubuserapp.data.network.GithubUsersDataSource
import com.example.githubuserapp.data.response.UsersResponse
import com.example.githubuserapp.data.response.model.ItemsItem
import com.example.githubuserapp.external.utils.LogUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GithubUsersRepositoryImpl(
    private val dataSource: GithubUsersDataSource
) : GithubUsersRepository {
    override suspend fun getSearchUsers(query: String?): Flow<UsersResponse> {
        return flow {
            try {
                val data = dataSource.getSearchUsers(query = query)
                emit(data)
            } catch (e: Throwable) {
                LogUtils.print(e)
                error(e.message.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getDetailUsers(username: String?): Flow<ItemsItem> {
        return flow {
            try {
                val data = dataSource.getDetailUsers(username = username)
                emit(data)
            } catch (e: Throwable) {
                LogUtils.print(e)
                error(e.message.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getFollowers(username: String?): Flow<List<ItemsItem>> {
        return flow {
            try {
                val data = dataSource.getFollowers(username = username)
                emit(data)
            } catch (e: Throwable) {
                LogUtils.print(e)
                error(e.message.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getFollowing(username: String?): Flow<List<ItemsItem>> {
        return flow {
            try {
                val data = dataSource.getFollowing(username = username)
                emit(data)
            } catch (e: Throwable) {
                LogUtils.print(e)
                error(e.message.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}