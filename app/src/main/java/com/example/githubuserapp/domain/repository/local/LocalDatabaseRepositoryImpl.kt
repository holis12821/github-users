package com.example.githubuserapp.domain.repository.local

import com.example.githubuserapp.data.local.dao.FavoriteDao
import com.example.githubuserapp.data.response.model.ItemsItem
import com.example.githubuserapp.external.utils.LogUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LocalDatabaseRepositoryImpl(private val favoriteDao: FavoriteDao): LocalDatabaseRepository {

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
     */

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