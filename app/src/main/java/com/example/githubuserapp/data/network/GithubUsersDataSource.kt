/**
 * Github Users Apps
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 17/09/21 03:36 PM
 * Last modified 17/09/21 03:36 PM by Nurholis*/
package com.example.githubuserapp.data.network

import com.example.githubuserapp.BuildConfig
import com.example.githubuserapp.data.response.DetailUsersResponse
import com.example.githubuserapp.data.response.ItemsItem
import com.example.githubuserapp.data.response.UsersResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubUsersDataSource {
    /**
     * This interface to handle service request and response from servers.
     * request asking to data such as JSON and response provided from servers
     * must be decode in order to be used and mapping to the object data.
     * this service applies the coroutine flow.*/

    @Headers("Authorization: ${BuildConfig.TOKEN}")
    @GET("search/users")
  suspend  fun getSearchUsers(
        @Query("q") query: String?
    ): UsersResponse

    @Headers("Authorization: ${BuildConfig.TOKEN}")
    @GET("users/{username}")
    suspend fun getDetailUsers(
        @Path("username") username: String?
    ): DetailUsersResponse

    @Headers("Authorization: ${BuildConfig.TOKEN}")
    @GET("users/{username}/followers")
    suspend fun getFollowers(
        @Path("username") username: String?
    ): List<ItemsItem>

    @Headers("Authorization: ${BuildConfig.TOKEN}")
    @GET("users/{username}/following")
    suspend fun getFollowing(
        @Path("username") username: String?
    ): List<ItemsItem>
}