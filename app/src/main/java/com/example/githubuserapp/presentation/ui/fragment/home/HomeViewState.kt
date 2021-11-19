package com.example.githubuserapp.presentation.ui.fragment.home

import com.example.githubuserapp.data.response.model.ItemsItem

sealed class HomeViewState {
    object Init: HomeViewState()
    data class Progress(val isLoading: Boolean): HomeViewState()
    data class ShowMessage(val message: String): HomeViewState()
    data class ShowSearchUsers(val list: List<ItemsItem>?): HomeViewState()
}
