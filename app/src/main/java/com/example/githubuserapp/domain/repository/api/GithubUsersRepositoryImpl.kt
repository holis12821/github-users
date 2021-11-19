/**
 * Github Users Apps
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 17/09/21 03:36 PM
 * Last modified 17/09/21 03:36 PM by Nurholis*/
package com.example.githubuserapp.domain.repository.api

import com.example.githubuserapp.data.local.dao.FavoriteDao
import com.example.githubuserapp.data.network.GithubUsersDataSource
import com.example.githubuserapp.data.response.UsersResponse
import com.example.githubuserapp.data.response.model.ItemsItem
import com.example.githubuserapp.external.utils.LogUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * A Repository manages queries and allows you to use multiple backend.
 *
 * The DAO is passed into the repository constructor as opposed to the whole database.
 * This is because it only needs access to the DAO, since the DAO contains all the
 * read/write methods for the database.
 * There's no need to expose the entire database to the repository.
 *
 * Data source to handle service from api in the repository, you You only emit from data
 * response that can be from servers.
 *
 * @param favoriteDao - Pass the daoFavorite as the parameter.
 * @param dataSource - Pass the dataSource as the service
 */

class GithubUsersRepositoryImpl(
    private val dataSource: GithubUsersDataSource,
    private val favoriteDao: FavoriteDao
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
            val user = favoriteDao.getUserDetail(username = username)
            if (user != null) {
                emit(user)
            } else {
                try {
                    val data = dataSource.getDetailUsers(username = username)
                    emit(data)
                } catch (e: Throwable) {
                    LogUtils.print(e)
                    error(e.message.toString())
                }
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

    //local database
    /**
     * By default Room runs suspend queries off the main thread, therefore, we don't need to
     * implement anything else to ensure we're not doing long running database work
     * off the main thread.
     */
    override suspend fun insertFavUsersData(favUsers: ItemsItem?) {
        favoriteDao.insertFavDetailUsers(favUsers = favUsers)
    }

    /**
     * Room executes all queries on a separate thread.
     * Observed Flow will emit data end notify the observer
     * when the data has changed.
     */
    override suspend fun favoriteUsers(): Flow<List<ItemsItem>> {
        return flow {
            try {
                val data = favoriteDao.getFavoriteUsersList()
                emit(data)
            } catch (e: Throwable) {
                LogUtils.print(e)
                error(e.message.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun updateFavUsersData(favUsers: ItemsItem?) {
        favoriteDao.updateFavUsersDetails(favUsers = favUsers)
    }

    override suspend fun deleteFavUsersData(favUsers: ItemsItem?) {
        favoriteDao.deleteFavUsersDetails(favUsers = favUsers)
    }
}