/**
 * Github Users Apps
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 15/09/21 5:47 PM
 * Last modified 15/09/21 5:47 PM by Nurholis*/
package com.example.githubuserapp.presentation.ui.activity

/**
 * sealed class used to representation a finite class hierarchy.
 * @Sealed support developers to maintain data types of predefined types.
 * To create closed class, we need to use the command "sealed" as a modifier for the class
 * */
sealed class MainViewState<out R> {
    data class Success<out T>(val data: T): MainViewState<T>()
    data class Error(val throwable: Throwable): MainViewState<Nothing>()
}
