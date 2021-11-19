/**
 * Github Users Apps
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 17/09/21 03:36 PM
 * Last modified 17/09/21 03:36 PM by Nurholis*/
package com.example.githubuserapp.di

import com.example.githubuserapp.presentation.ui.activity.splashscreen.SplashScreenViewModel
import com.example.githubuserapp.presentation.ui.fragment.detailusers.DetailFragmentViewModel
import com.example.githubuserapp.presentation.ui.fragment.favorite.FavoriteFragmentViewModel
import com.example.githubuserapp.presentation.ui.fragment.followers.FollowersFragmentsViewModel
import com.example.githubuserapp.presentation.ui.fragment.following.FollowingFragmentsViewModel
import com.example.githubuserapp.presentation.ui.fragment.home.HomeFragmentViewModel
import com.example.githubuserapp.presentation.ui.fragment.setting.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { DetailFragmentViewModel(get(), get(), get(), get())}
    viewModel { FollowersFragmentsViewModel(get()) }
    viewModel { FollowingFragmentsViewModel(get()) }
    viewModel { FavoriteFragmentViewModel(get()) }
    viewModel { HomeFragmentViewModel(get()) }
    viewModel { SettingsViewModel(get(), get()) }
    viewModel { SplashScreenViewModel(get()) }
}