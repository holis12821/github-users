package com.example.githubuserapp.data.local.dao

import androidx.room.*
import com.example.githubuserapp.data.response.DetailUsersResponse
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    /**
     * This query is used to access database which will
     * sort the data by username
     * */
    @Query("SELECT * FROM table_user ORDER BY login ASC")
    suspend fun all(): List<DetailUsersResponse>

    /**
     * This query is used to access database which will insert
     * data into database, but when there is conflict data when
     * entered it will be replaced
     * */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun add(entity: DetailUsersResponse)

   @Query("SELECT * FROM table_user WHERE login = :username")
   suspend fun getUserDetail(username: String): DetailUsersResponse

    /**
     * This query is used to access database which will delete data
     * or entity into database*/
    @Delete
   suspend fun delete(entity: DetailUsersResponse)
}