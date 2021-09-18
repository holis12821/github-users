package com.example.githubuserapp.di

import com.example.githubuserapp.data.network.GithubUsersDataSource
import com.example.githubuserapp.domain.repository.GithubUsersRepository
import com.example.githubuserapp.domain.repository.GithubUsersRepositoryImpl
import org.koin.dsl.module
import retrofit2.Retrofit

val repositoryModule = module {
    single { provideGithubUsersDataSource(get()) }
    single { provideGithubUsersRepository(get()) }
}

fun provideGithubUsersDataSource(retrofit: Retrofit): GithubUsersDataSource {
    return retrofit.create(GithubUsersDataSource::class.java)
}

fun provideGithubUsersRepository(dataSource: GithubUsersDataSource): GithubUsersRepository {
    return GithubUsersRepositoryImpl(dataSource)
}