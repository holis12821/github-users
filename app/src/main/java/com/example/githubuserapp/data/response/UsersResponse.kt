/**
 * Github Users Apps
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 17/09/21 03:36 PM
 * Last modified 17/09/21 03:36 PM by Nurholis*/
package com.example.githubuserapp.data.response

import android.os.Parcelable
import com.example.githubuserapp.data.response.model.ItemsItem
import kotlinx.android.parcel.Parcelize

/**
 * This class used base response to handle API.
 */
@Parcelize
data class UsersResponse(val totalCount: Int = 0,
                         val incompleteResults: Boolean = false,
                         val items: List<ItemsItem>? = null): Parcelable