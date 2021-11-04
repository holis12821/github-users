/**
 * Github Users Apps
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 15/09/21 01:00 PM
 * Last modified 15/09/21 01:00 PM by Nurholis*/
package com.example.githubuserapp.presentation.ui.activity.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubuserapp.data.response.model.ItemsItem
import com.example.githubuserapp.domain.usecase.GithubUsersUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainViewModel(
    private val gitHubUsersUseCase: GithubUsersUseCase
) : ViewModel() {
    //init state
    private val _stateData = MutableLiveData<MainViewState>(MainViewState.Init)
    val stateData: LiveData<MainViewState> get() = _stateData

    fun getSearchUsers(query: String) {
        viewModelScope.launch {
            gitHubUsersUseCase.execute(query = query)
                .onStart { showLoading() }
                .catch { e ->
                    hideLoading()
                    showMessage(e.message)
                }
                .collect { result ->
                    hideLoading()
                    //handle data
                    showListSearchUsers(result.items)
                }

        }
    }

    private fun showLoading() {
        _stateData.value = MainViewState.Progress(isLoading = true)
    }

    private fun hideLoading() {
        _stateData.value = MainViewState.Progress(isLoading = false)
    }

    private fun showMessage(message: String?) {
        if (!message.isNullOrEmpty()) {
            _stateData.value = MainViewState.ShowMessage(message)
        }
    }

    private fun showListSearchUsers(list: List<ItemsItem>?) {
        _stateData.value = MainViewState.ShowSearchUsers(list = list)
    }
}