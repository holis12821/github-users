package com.example.githubuserapp.domain.repository.local

import com.example.githubuserapp.data.response.DetailUsersResponse
import kotlinx.coroutines.flow.Flow

interface LocalDatabaseRepository {
    suspend fun saveFavoriteUsers(entity: DetailUsersResponse)
    suspend fun deleteFavoriteUsers(entity: DetailUsersResponse)
    suspend fun getAllFavoriteUsers(): Flow<List<DetailUsersResponse>>

}