package com.example.githubuserapp.domain.repository.local

import com.example.githubuserapp.data.response.model.ItemsItem
import kotlinx.coroutines.flow.Flow

interface LocalDatabaseRepository {
    //local database repository
    suspend fun insertFavUsersData(favUsers: ItemsItem?)
    suspend fun updateFavUsersData(favUsers: ItemsItem?)
    suspend fun deleteFavUsersData(favUsers: ItemsItem?)
    suspend fun favoriteUsers(): Flow<List<ItemsItem>>
}