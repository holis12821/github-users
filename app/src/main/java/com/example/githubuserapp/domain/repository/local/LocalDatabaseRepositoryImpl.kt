package com.example.githubuserapp.domain.repository.local

import com.example.githubuserapp.data.local.dao.FavoriteDao
import com.example.githubuserapp.data.response.DetailUsersResponse
import com.example.githubuserapp.external.utils.LogUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LocalDatabaseRepositoryImpl(private val daoFavoriteDao: FavoriteDao): LocalDatabaseRepository {

    override suspend fun saveFavoriteUsers(entity: DetailUsersResponse) {
        daoFavoriteDao.add(entity = entity)
    }

    override suspend fun deleteFavoriteUsers(entity: DetailUsersResponse) {
        daoFavoriteDao.delete(entity = entity)
    }

    override suspend fun getAllFavoriteUsers(): Flow<List<DetailUsersResponse>> {
        return flow {
            try {
                val data = daoFavoriteDao.all()
                delay(3000)
                emit(data)
            } catch (e: Throwable) {
                LogUtils.error(e.message.toString())
                error(e)
            }
        }.flowOn(Dispatchers.IO)
    }
}