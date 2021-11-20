package com.example.githubuserapp.data.local.dao

import androidx.room.*
import com.example.githubuserapp.data.response.model.ItemsItem

@Dao
interface FavoriteDao {
    /**
     * All queries must be executed on a separate thread. They cannot be execute
     * from Main Thread or it will cause an crash.
     *
     * Room has kotlin coroutine support.
     * This allows your queries to be annotated with the suspend modifier and
     * then called from a coroutine or from another suspension function.
     */

    /**
     * A function to insert favorite users details to the local database using room.
     *
     * @param favUsers - here we will pass the entity class that we have created.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavDetailUsers(favUsers: ItemsItem?)

    /**
     * When data changes, you usually want to take some action, such as displaying the updated data in the UI.
     * This means you have to observe the data so when it changes, you can react
     * To observe data changes we will use Flow from kotlinx-coroutines.
     * Use a return value of type Flow in your method description,
     * and Room generates all necessary code to update the Flow when the database is updated.
     *
     * A Flow is an async sequence of values
     * Flow produces values one at a time (instead of all at once) that can generate values from async operations
     * like network requests, database calls, or other async code.
     * It supports coroutines throughout its API, so you can transform a flow using coroutines as well!
     *
     * SQLite does not have a boolean data type. Room maps it to an INTEGER column, mapping true to 1 and false to 0.
     */
    @Query("SELECT * from table_user WHERE isFavorite = 1")
    suspend fun getFavoriteUsersList(): List<ItemsItem>

    /**
     * A function to update favorite dish details to the local database using Room.
     *
     * @param favUsers - Here we will pass the entity class that we have created with updated details along with "id".
     */
    @Update
    suspend fun updateFavUsersDetails(favUsers: ItemsItem?)

    /**
     * A function to delete favorite dish details from the local database using Room.
     *
     * @param favUsers - Here we will pass the entity class with details that we want to delete.
     */
    @Delete
    suspend fun deleteFavUsersDetails(favUsers: ItemsItem?)
}