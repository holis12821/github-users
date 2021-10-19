package com.example.githubuserapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.githubuserapp.data.local.db.dao.FavoriteDao
import com.example.githubuserapp.data.response.ItemsItem

@Database(entities = [ItemsItem::class], version = 1)
abstract class LocalDb: RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}