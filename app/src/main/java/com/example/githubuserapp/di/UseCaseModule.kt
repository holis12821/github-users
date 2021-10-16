/**
 * Github Users Apps
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 17/09/21 03:36 PM
 * Last modified 17/09/21 12:37 PM by Nurholis*/
package com.example.githubuserapp.di

import com.example.githubuserapp.domain.usecase.*
import org.koin.dsl.module

val useCaseModule = module {
    single { GithubUsersUseCase(get()) }
    single { DetailUsersUseCase(get()) }
    single { FollowersUseCase(get()) }
    single { FollowingUseCase(get()) }
    single { GetThemeDataStoreUseCase(get()) }
    single { SaveThemeDataStoreUseCase(get()) }
}