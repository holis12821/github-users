/**
 * Github Users Apps
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 17/09/21 03:36 PM
 * Last modified 17/09/21 03:36 PM by Nurholis*/
package com.example.githubuserapp.di

import com.example.githubuserapp.presentation.ui.activity.MainViewModel
import com.example.githubuserapp.presentation.ui.activity.detailuser.DetailUserViewModel
import com.example.githubuserapp.presentation.ui.activity.settings.SettingsViewModel
import com.example.githubuserapp.presentation.ui.activity.splashscreen.SplashScreenViewModel
import com.example.githubuserapp.presentation.ui.fragment.followers.FollowersFragmentsViewModel
import com.example.githubuserapp.presentation.ui.fragment.following.FollowingFragmentsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { DetailUserViewModel(get()) }
    viewModel { FollowersFragmentsViewModel(get()) }
    viewModel { FollowingFragmentsViewModel(get()) }
    viewModel { MainViewModel(get(), get(), get(), get()) }
    viewModel { SettingsViewModel(get(), get()) }
    viewModel { SplashScreenViewModel(get()) }
}