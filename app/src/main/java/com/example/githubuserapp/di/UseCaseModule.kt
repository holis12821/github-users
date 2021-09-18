package com.example.githubuserapp.di

import com.example.githubuserapp.domain.usecase.GithubUsersUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GithubUsersUseCase(get())}
}