package com.example.githubuserapp.domain.repository.local

import com.example.githubuserapp.data.response.ItemsItem
import com.example.githubuserapp.presentation.ui.activity.MainViewState
import kotlinx.coroutines.flow.Flow

interface LocalDatabaseRepository {
    suspend fun saveFavoriteUsers(entity: ItemsItem)
    suspend fun deleteFavoriteUsers(entity: ItemsItem)
    suspend fun getAllFavoriteUsers(): Flow<MainViewState<List<ItemsItem>>>

}