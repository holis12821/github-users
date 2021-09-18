package com.example.githubuserapp.domain.repository

import com.example.githubuserapp.data.network.GithubUsersDataSource
import com.example.githubuserapp.data.response.UsersResponse
import com.example.githubuserapp.presentation.ui.activity.MainActivityViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GithubUsersRepositoryImpl(
  private val  dataSource: GithubUsersDataSource
): GithubUsersRepository {
    override suspend fun getSearchUsers(query: String?): Flow<MainActivityViewState<UsersResponse>> {
        return flow {
            try {
                val data = dataSource.getSearchUsers(query = query)
                emit(MainActivityViewState.Success(data = data))
                delay(2000)
            } catch (e: Throwable) {
                emit(MainActivityViewState.Error(e))
            }
        }.flowOn(Dispatchers.IO)
    }
}