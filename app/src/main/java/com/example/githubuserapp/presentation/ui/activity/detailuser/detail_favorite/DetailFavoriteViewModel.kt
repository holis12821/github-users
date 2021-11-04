package com.example.githubuserapp.presentation.ui.activity.detailuser.detail_favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubuserapp.data.response.DetailUsersResponse
import com.example.githubuserapp.domain.usecase.GetDetailFavoriteUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class DetailFavoriteViewModel(
    private val getDetailFavoriteUseCase: GetDetailFavoriteUseCase
) : ViewModel() {
    //init state
    private val _stateData = MutableLiveData<DetailFavoriteViewState>(DetailFavoriteViewState.Init)
    val stateData: LiveData<DetailFavoriteViewState> get() = _stateData

    fun getFavoriteDetail(username: String) {
        viewModelScope.launch {
            getDetailFavoriteUseCase.execute(username = username)
                .onStart { showLoading() }
                .catch { e ->
                    hideLoading()
                    showMessage(e.message)
                }
                .collect { result ->
                    hideLoading()
                    showFavoriteDetail(result)
                }
        }
    }

    private fun showLoading() {
        _stateData.value = DetailFavoriteViewState.Progress(isLoading = true)
    }

    private fun hideLoading() {
        _stateData.value = DetailFavoriteViewState.Progress(isLoading = false)
    }

    private fun showMessage(message: String?) {
        if (!message.isNullOrEmpty()) {
            _stateData.value = DetailFavoriteViewState.ShowMessage(message)
        }
    }

    private fun showFavoriteDetail(detailUsersResponse: DetailUsersResponse) {
        _stateData.value = DetailFavoriteViewState.ShowFavoriteDetail(detailUsersResponse)
    }

}