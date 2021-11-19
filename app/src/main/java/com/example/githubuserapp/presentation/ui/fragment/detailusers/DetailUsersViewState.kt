package com.example.githubuserapp.presentation.ui.fragment.detailusers

import com.example.githubuserapp.data.response.model.ItemsItem

sealed class DetailUsersViewState {
    object Init: DetailUsersViewState()
    data class Progress(val isLoading: Boolean): DetailUsersViewState()
    data class ShowMessage(val message: String): DetailUsersViewState()
    data class ShowDetailUser(val data: ItemsItem?): DetailUsersViewState()
}
