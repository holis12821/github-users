package com.example.githubuserapp.domain.repository.local

import com.example.githubuserapp.data.local.db.LocalDb
import com.example.githubuserapp.data.local.db.dao.FavoriteDao
import com.example.githubuserapp.data.response.ItemsItem
import com.example.githubuserapp.external.utils.LogUtils
import com.example.githubuserapp.presentation.ui.activity.MainViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LocalDatabaseRepositoryImpl(private val daoFavoriteDao: FavoriteDao): LocalDatabaseRepository {
    override suspend fun saveFavoriteUsers(entity: ItemsItem) {
        delay(3000)
        daoFavoriteDao.add(entity = entity)
    }

    override suspend fun deleteFavoriteUsers(entity: ItemsItem) {
        delay(3000)
        daoFavoriteDao.delete(entity = entity)
    }

    override suspend fun getAllFavoriteUsers(): Flow<MainViewState<List<ItemsItem>>> {
        return flow {
            try {
                val data = daoFavoriteDao.all()
                delay(3000)
                emit(MainViewState.Success(data = data))
            } catch (e: Throwable) {
                emit(MainViewState.Error(e))
                LogUtils.print(e)
            }
        }.flowOn(Dispatchers.IO)
    }

}