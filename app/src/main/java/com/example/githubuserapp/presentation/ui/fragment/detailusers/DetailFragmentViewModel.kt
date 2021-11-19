/**
 * Github Users Apps
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 15/09/21 01:00 PM
 * Last modified 15/09/21 13.00 PM by Nurholis*/
package com.example.githubuserapp.presentation.ui.fragment.detailusers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubuserapp.data.response.model.ItemsItem
import com.example.githubuserapp.domain.usecase.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class DetailFragmentViewModel(
    private val usersUseCase: DetailUsersUseCase,
    private val addFavoriteUsersUseCase: AddFavoriteUsersUseCase,
    private val removeFavoriteUsersUseCase: RemoveFavoriteUsersUseCase,
    private val updateFavoriteUsersUseCase: UpdateFavoriteUsersUseCase
) : ViewModel() {

    //init state
    private val _stateData = MutableLiveData<DetailUsersViewState>(DetailUsersViewState.Init)
    val stateData: LiveData<DetailUsersViewState> get() = _stateData


    fun getDetailUsers(username: String) {
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
        _stateData.value = DetailUsersViewState.Progress(isLoading = true)
    }

    private fun hideLoading() {
        _stateData.value = DetailUsersViewState.Progress(isLoading = false)
    }

    private fun showMessage(message: String?) {
        if (!message.isNullOrEmpty()) {
            _stateData.value = DetailUsersViewState.ShowMessage(message)
        }
    }

    private fun showDetailUser(itemsItem: ItemsItem?) {
        _stateData.value = DetailUsersViewState.ShowDetailUser(itemsItem)
        saveAsFavorite(itemsItem)
    }

    fun saveAsFavorite(favUsers: ItemsItem?) {
        viewModelScope.launch(Dispatchers.IO) {
            favUsers?.let {
                addFavoriteUsersUseCase.execute(favUsers = favUsers)
            }
        }
    }

    fun removeAsFavorite(favUsers: ItemsItem?) {
        viewModelScope.launch(Dispatchers.IO) {
            favUsers?.let {
                removeFavoriteUsersUseCase.execute(favUsers = favUsers)
            }
        }
    }

    fun updateAsFavorite(favUsers: ItemsItem?) {
        viewModelScope.launch(Dispatchers.IO) {
            favUsers?.let {
                updateFavoriteUsersUseCase.execute(favUsers = favUsers)
            }
        }
    }
}