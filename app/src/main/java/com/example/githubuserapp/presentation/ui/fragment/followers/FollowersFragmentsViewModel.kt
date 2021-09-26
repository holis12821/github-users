/**
 * Github Users Apps
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 25/09/21 04.55 PM
 * Last modified 15/09/21 13.00 PM by Nurholis*/
package com.example.githubuserapp.presentation.ui.fragment.followers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubuserapp.data.response.ItemsItem
import com.example.githubuserapp.domain.usecase.FollowersUseCase
import com.example.githubuserapp.presentation.ui.activity.MainViewState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class FollowersFragmentsViewModel(
    private val useCase: FollowersUseCase
) : ViewModel() {
    //init state
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _onError = MutableLiveData<Throwable>()
    val onError: LiveData<Throwable> get() = _onError

    private val _onSuccess = MutableLiveData<List<ItemsItem>?>()
    val onSuccess: LiveData<List<ItemsItem>?> get() = _onSuccess

    fun getFollowers(username: String) {
        viewModelScope.launch {
            useCase.invoke(username = username)
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