/**
 * Github Users Apps
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 15/09/21 13:00 PM
 * Last modified 15/09/21 13:00 PM by Nurholis*/
package com.example.githubuserapp.external.utils

import com.example.githubuserapp.BuildConfig
import timber.log.Timber

object LogUtils {
    fun error(message: String) {
        if (BuildConfig.DEBUG) {
            Timber.e(message)
        }
    }

    fun info(message: String) {
        if (BuildConfig.DEBUG) {
            Timber.i(message)
        }
    }

    fun print(throwable: Throwable?) {
        if (BuildConfig.DEBUG) {
            throwable?.printStackTrace()
        }
    }
}