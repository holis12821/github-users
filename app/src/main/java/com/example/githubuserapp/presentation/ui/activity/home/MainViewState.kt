/**
 * Github Users Apps
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 15/09/21 5:47 PM
 * Last modified 15/09/21 5:47 PM by Nurholis*/
package com.example.githubuserapp.presentation.ui.activity.home

import com.example.githubuserapp.data.response.UsersResponse
import com.example.githubuserapp.data.response.model.ItemsItem

/**
 * sealed class used to representation a finite class hierarchy.
 * @Sealed support developers to maintain data types of predefined types.
 * To create closed class, we need to use the command "sealed" as a modifier for the class
 * */
sealed class MainViewState {
    object Init: MainViewState()
    data class Progress(val isLoading: Boolean): MainViewState()
    data class ShowMessage(val message: String): MainViewState()
    data class ShowSearchUsers(val list: List<ItemsItem>?): MainViewState()
}
