/**
 * Github Users Apps
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 15/09/21 13.00 PM
 * Last modified 15/09/21 13.00 PM by Nurholis*/
package com.example.githubuserapp.external.constant
//constant reusable variable
import androidx.annotation.StringRes
import com.example.githubuserapp.R
//constant to handle time connection to request
const val networkConnectTimeOut = 30L
const val networkWriteTimeout = 30L
const val networkReadTimeOut = 30L

//key constant to handle shared data via bundle, intent etc.
const val KEY_EXTRA_USERS = "extra_users"

@StringRes
val TAB_TITLES_FRAGMENT = intArrayOf(
    R.string.followers,
    R.string.Following
)