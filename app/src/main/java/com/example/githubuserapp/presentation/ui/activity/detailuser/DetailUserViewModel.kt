/**
 * Github Users Apps
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 15/09/21 01:00 PM
 * Last modified 15/09/21 13.00 PM by Nurholis*/
package com.example.githubuserapp.presentation.ui.activity.detailuser

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubuserapp.data.response.DetailUsersResponse
import com.example.githubuserapp.domain.usecase.DetailUsersUseCase
import com.example.githubuserapp.presentation.ui.activity.MainViewState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class DetailUserViewModel(
    private val usersUseCase: DetailUsersUseCase
): ViewModel() {
    //init state
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _onError = MutableLiveData<Throwable>()
    val onError: LiveData<Throwable> get() = _onError

    private val _onSuccess = MutableLiveData<DetailUsersResponse?>()
    val onSuccess: LiveData<DetailUsersResponse?> get() = _onSuccess

    fun getDetailUsers(username: String) {
        viewModelScope.launch {
            usersUseCase.invoke(username = username)
                .onStart {
                    _isLoading.value = true
                }.onCompletion {
                    _isLoading.value = false
                }.collect {
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

}