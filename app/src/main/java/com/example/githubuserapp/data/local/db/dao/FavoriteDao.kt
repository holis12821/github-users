package com.example.githubuserapp.data.local.db.dao

import androidx.room.*
import com.example.githubuserapp.data.response.ItemsItem

@Dao
interface FavoriteDao {
    /**
     * This query is used to access database which will
     * sort the data by username
     * */
    @Query("SELECT * FROM ItemsItem ORDER BY login")
    suspend fun all(): List<ItemsItem>

    /**
     * This query is used to access database which will insert
     * data into database, but when there is conflict data when
     * entered it will be replaced
     * */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun add(entity: ItemsItem)
    /**
     * This query is used to access database which will delete data
     * or entity into database*/
    @Delete
   suspend fun delete(entity: ItemsItem)
}