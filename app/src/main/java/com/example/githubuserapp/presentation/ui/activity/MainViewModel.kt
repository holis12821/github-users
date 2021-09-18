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
import com.example.githubuserapp.BuildConfig
import com.example.githubuserapp.data.response.ItemsItem
import com.example.githubuserapp.data.response.UsersResponse
import com.example.githubuserapp.domain.usecase.GithubUsersUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(
    private val useCase: GithubUsersUseCase
): ViewModel() {
    //init state

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _isError = MutableLiveData<Throwable>()
    val isError: LiveData<Throwable> get() = _isError

   private val _onSuccess = MutableLiveData<UsersResponse?>()

   fun getSearchUsers(query: String) {
       viewModelScope.launch {
           useCase.invoke(query = query)
               .onStart {
                   _isLoading.value = true
               }.onCompletion {
                   _isLoading.value = false
               }
               .collect {
                   when(it) {
                       is MainActivityViewState.Success -> {
                           _onSuccess.value = it.data
                       }
                       is MainActivityViewState.Error -> {
                           _isError.value = it.throwable
                       }
                   }
               }
       }
   }

    fun onSuccess(): LiveData<UsersResponse?> {
        return _onSuccess
    }
}