/**
 * Github Users Apps
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 17/09/21 03:36 PM
 * Last modified 17/09/21 03:36 PM by Nurholis*/
package com.example.githubuserapp.data.network

import com.example.githubuserapp.BuildConfig
import com.example.githubuserapp.data.response.UsersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface GithubUsersDataSource {

    @Headers("Authorization: ${BuildConfig.TOKEN}")
    @GET("search/users")
  suspend  fun getSearchUsers(
        @Query("q") query: String?
    ): UsersResponse


}