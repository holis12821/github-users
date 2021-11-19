package com.example.githubuserapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.githubuserapp.data.local.dao.FavoriteDao
import com.example.githubuserapp.data.response.model.ItemsItem

/**
 * This Local Database using room.
 * class that becomes a class for database
 * room as well as made abstract class
 * to interact use DAO(Data Access Object) to get query SQL*/
@Database(entities = [ItemsItem::class], version = 1, exportSchema = false)
abstract class LocalDb: RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}