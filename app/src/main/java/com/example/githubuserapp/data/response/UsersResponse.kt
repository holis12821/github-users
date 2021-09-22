/**
 * Github Users Apps
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 17/09/21 03:36 PM
 * Last modified 17/09/21 03:36 PM by Nurholis*/
package com.example.githubuserapp.data.response

import com.google.gson.annotations.SerializedName
/**
 * This class used base response to handle API.*/
data class UsersResponse(@SerializedName("total_count")
                         val totalCount: Int = 0,
                         @SerializedName("incomplete_results")
                         val incompleteResults: Boolean = false,
                         @SerializedName("items")
                         val items: List<ItemsItem>? = null)