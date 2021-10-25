/**
 * Github Users Apps
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 25/09/21 04.55 PM
 * Last modified 15/09/21 13.00 PM by Nurholis*/
package com.example.githubuserapp.presentation.ui.fragment.following

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubuserapp.data.response.model.ItemsItem
import com.example.githubuserapp.domain.usecase.FollowingUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class FollowingFragmentsViewModel(
    private val useCase: FollowingUseCase
) : ViewModel() {
    //init state
    private val _stateData = MutableLiveData<FollowingViewState>(FollowingViewState.Init)
    val stateData: LiveData<FollowingViewState> get() = _stateData

    private var username = ""

    init {
        getFollowing(username = username)
    }

    fun setUserName(username: String) {
        this.username = username
    }

    private fun getFollowing(username: String) {
        viewModelScope.launch {
            useCase.execute(username = username)
                .onStart { showLoading() }
                .catch { e ->
                    hideLoading()
                    showMessage(e.message)
                }
                .collect { result ->
                    hideLoading()
                    showFollowing(result)
                }
        }
    }

    private fun showLoading() {
        _stateData.value = FollowingViewState.Progress(isLoading = true)
    }

    private fun hideLoading() {
        _stateData.value = FollowingViewState.Progress(isLoading = false)
    }

    private fun showMessage(message: String?) {
        if (!message.isNullOrEmpty()) {
            _stateData.value = FollowingViewState.ShowMessage(message)
        }
    }

    private fun showFollowing(list: List<ItemsItem>?) {
        _stateData.value = FollowingViewState.ShowFollowing(list)
    }

}