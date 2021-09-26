/**
 * Github Users Apps
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 17/09/21 03:36 PM
 * Last modified 17/09/21 03:36 PM by Nurholis*/
package com.example.githubuserapp.domain.repository

import com.example.githubuserapp.data.network.GithubUsersDataSource
import com.example.githubuserapp.data.response.DetailUsersResponse
import com.example.githubuserapp.data.response.ItemsItem
import com.example.githubuserapp.data.response.UsersResponse
import com.example.githubuserapp.external.utils.LogUtils
import com.example.githubuserapp.presentation.ui.activity.MainViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GithubUsersRepositoryImpl(
  private val  dataSource: GithubUsersDataSource
): GithubUsersRepository {
    override suspend fun getSearchUsers(query: String?): Flow<MainViewState<UsersResponse>> {
        return flow {
            try {
                val data = dataSource.getSearchUsers(query = query)
                delay(3000)
                emit(MainViewState.Success(data = data))
            } catch (e: Throwable) {
                emit(MainViewState.Error(e))
                LogUtils.print(e)
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getDetailUsers(username: String?): Flow<MainViewState<DetailUsersResponse>> {
        return flow {
            try {
                val data = dataSource.getDetailUsers(username = username)
                delay(3000)
                emit(MainViewState.Success(data = data))
            } catch (e: Throwable) {
                emit(MainViewState.Error(e))
                LogUtils.print(e)
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getFollowers(username: String?): Flow<MainViewState<List<ItemsItem>>> {
        return flow {
            try {
                val data = dataSource.getFollowers(username = username)
                delay(3000)
                emit(MainViewState.Success(data = data))
            } catch (e: Throwable) {
                emit(MainViewState.Error(e))
                LogUtils.print(e)
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getFollowing(username: String?): Flow<MainViewState<List<ItemsItem>>> {
        return flow {
            try {
                val data = dataSource.getFollowing(username = username)
                delay(3000)
                emit(MainViewState.Success(data = data))
            } catch (e: Throwable) {
                emit(MainViewState.Error(e))
                LogUtils.print(e)
            }
        }.flowOn(Dispatchers.IO)
    }
}