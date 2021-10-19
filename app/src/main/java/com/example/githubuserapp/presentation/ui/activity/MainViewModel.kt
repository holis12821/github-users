/**
 * Github Users Apps
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 15/09/21 01:00 PM
 * Last modified 15/09/21 01:00 PM by Nurholis*/
package com.example.githubuserapp.presentation.ui.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubuserapp.data.response.ItemsItem
import com.example.githubuserapp.data.response.UsersResponse
import com.example.githubuserapp.domain.usecase.AddFavoriteUsersUseCase
import com.example.githubuserapp.domain.usecase.GetFavoriteUsersUseCase
import com.example.githubuserapp.domain.usecase.GithubUsersUseCase
import com.example.githubuserapp.domain.usecase.RemoveFavoriteUsersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(
    private val gitHubUsersUseCase: GithubUsersUseCase,
    private val addFavoriteUsersUseCase: AddFavoriteUsersUseCase,
    private val removeFavoriteUsersUseCase: RemoveFavoriteUsersUseCase,
    private val getFavoriteUsersUseCase: GetFavoriteUsersUseCase
): ViewModel() {
    //init state

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _onError = MutableLiveData<Throwable>()
    val onError: LiveData<Throwable> get() = _onError

    //using livedata from API
   private val _onSuccess = MutableLiveData<UsersResponse?>()
   val onSuccess: LiveData<UsersResponse?> get() = _onSuccess

   //using livedata from local database
   private val _onSuccessFavoriteUsers = MutableLiveData<List<ItemsItem>?>()
    val onSuccessFavoriteUsers: LiveData<List<ItemsItem>?> get() = _onSuccessFavoriteUsers

   fun getSearchUsers(query: String) {
       viewModelScope.launch {
           gitHubUsersUseCase.execute(query = query)
               .onStart {
                   _isLoading.value = true
               }.onCompletion {
                   _isLoading.value = false
               }
               .collect {
                   when(it) {
                       is MainViewState.Success -> {
                           _onSuccess.value = it.data
                       }
                       is MainViewState.Error -> {
                           _onError.value = it.throwable
                       }
                   }
               }
       }
   }

    fun saveAsFavorite(entity: ItemsItem) {
        viewModelScope.launch(Dispatchers.IO) {
            addFavoriteUsersUseCase.execute(entity = entity)
        }
    }

    fun removeAsFavorite(entity: ItemsItem) {
        viewModelScope.launch(Dispatchers.IO) {
            removeFavoriteUsersUseCase.execute(entity = entity)
        }
    }

    fun getFavoriteUsers() {
        viewModelScope.launch {
            getFavoriteUsersUseCase.execute()
                .onStart {
                    _isLoading.value = true
                }.onCompletion {
                    _isLoading.value = false
                }
                .collect {
                    when(it) {
                        is MainViewState.Success -> {
                           _onSuccessFavoriteUsers.value = it.data
                        }
                        is MainViewState.Error -> {
                            _onError.value = it.throwable
                        }
                    }
                }
        }
    }
}