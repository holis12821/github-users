package com.example.githubuserapp.presentation.ui.activity.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubuserapp.data.response.DetailUsersResponse
import com.example.githubuserapp.domain.usecase.GetFavoriteUsersUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val getFavoriteUsersUseCase: GetFavoriteUsersUseCase
): ViewModel() {
    //init state
    private val _stateData = MutableLiveData<FavoriteViewState>(FavoriteViewState.Init)
    val stateData: LiveData<FavoriteViewState> get() = _stateData

    init {
        getFavoriteUsers()
    }

    private fun getFavoriteUsers() {
        viewModelScope.launch {
            getFavoriteUsersUseCase.execute()
                .onStart { showLoading()  }
                .catch { e ->
                    hideLoading()
                    showMessage(e.message)
                }
                .collect { result ->
                    hideLoading()
                    showFavoriteUsers(result)
                }
        }
    }


    private fun showLoading() {
        _stateData.value = FavoriteViewState.Progress(isLoading = true)
    }

    private fun hideLoading() {
        _stateData.value = FavoriteViewState.Progress(isLoading = false)
    }

    private fun showMessage(message: String?) {
        if (!message.isNullOrEmpty()) {
            _stateData.value = FavoriteViewState.ShowMessage(message)
        }
    }

    private fun showFavoriteUsers(list: List<DetailUsersResponse>?) {
        _stateData.value = FavoriteViewState.ShowDataFavorite(list = list)
    }
}