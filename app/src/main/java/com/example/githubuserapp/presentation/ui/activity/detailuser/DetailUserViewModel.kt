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
import com.example.githubuserapp.domain.usecase.AddFavoriteUsersUseCase
import com.example.githubuserapp.domain.usecase.DetailUsersUseCase
import com.example.githubuserapp.domain.usecase.RemoveFavoriteUsersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class DetailUserViewModel(
    private val usersUseCase: DetailUsersUseCase,
    private val addFavoriteUsersUseCase: AddFavoriteUsersUseCase,
    private val removeFavoriteUsersUseCase: RemoveFavoriteUsersUseCase
): ViewModel() {
    //init state
    private val _stateData = MutableLiveData<DetailUserViewState>(DetailUserViewState.Init)
    val stateData: LiveData<DetailUserViewState> get() = _stateData

    private lateinit var detailUsersResponse: DetailUsersResponse

    private var username = ""

    init {
        getDetailUsers(username = username)
    }

    fun init (detailUsersResponse: DetailUsersResponse) {
        this.detailUsersResponse = detailUsersResponse
    }

    fun getDetailUsers(): DetailUsersResponse = detailUsersResponse

    fun setUsername(username: String) {
        this.username = username
    }

    private fun getDetailUsers(username: String) {
        viewModelScope.launch {
            usersUseCase.execute(username = username)
                .onStart { showLoading() }
                .catch { e ->
                    hideLoading()
                    showMessage(e.message)
                }
                .collect { result ->
                    hideLoading()
                    showDetailUser(result)
                }

        }
    }

    private fun showLoading() {
        _stateData.value = DetailUserViewState.Progress(isLoading = true)
    }

    private fun hideLoading() {
        _stateData.value = DetailUserViewState.Progress(isLoading = false)
    }

    private fun showMessage(message: String?) {
        if (!message.isNullOrEmpty()) {
            _stateData.value = DetailUserViewState.ShowMessage(message)
        }
    }

    private fun showDetailUser(detailUsersResponse: DetailUsersResponse) {
        _stateData.value = DetailUserViewState.ShowDetailUser(detailUsersResponse)
    }

    fun saveAsFavorite(entity: DetailUsersResponse?) {
        viewModelScope.launch(Dispatchers.IO) {
            entity?.let {
                addFavoriteUsersUseCase.execute(entity = it)
                _stateData.postValue(DetailUserViewState.Favorite(isFavorite = true))
            }
        }
    }

    fun removeAsFavorite(entity: DetailUsersResponse?) {
        viewModelScope.launch(Dispatchers.IO) {
            entity?.let {
                removeFavoriteUsersUseCase.execute(entity = it)
                _stateData.postValue(DetailUserViewState.Favorite(isFavorite = false))
            }
        }
    }
}