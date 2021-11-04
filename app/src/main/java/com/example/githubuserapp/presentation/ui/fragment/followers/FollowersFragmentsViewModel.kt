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
import com.example.githubuserapp.data.response.model.ItemsItem
import com.example.githubuserapp.domain.usecase.FollowersUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class FollowersFragmentsViewModel(
    private val useCase: FollowersUseCase
) : ViewModel() {
    //init state
    private val _stateData = MutableLiveData<FollowersViewState>(FollowersViewState.Init)
    val stateData: LiveData<FollowersViewState> get() = _stateData

    fun getFollowers(username: String) {
        viewModelScope.launch {
            useCase.execute(username = username)
                .onStart { showLoading() }
                .catch { e ->
                    hideLoading()
                    showMessage(e.message)
                }
                .collect { result ->
                    hideLoading()
                    showFollowers(result)
                }
        }
    }

    private fun showLoading() {
        _stateData.value = FollowersViewState.Progress(isLoading = true)
    }

    private fun hideLoading() {
        _stateData.value = FollowersViewState.Progress(isLoading = false)
    }

    private fun showMessage(message: String?) {
        if (!message.isNullOrEmpty()) {
            _stateData.value = FollowersViewState.ShowMessage(message)
        }
    }

    private fun showFollowers(list: List<ItemsItem>?) {
        _stateData.value = FollowersViewState.ShowFollowers(list)
    }

}