package com.example.githubuserapp.presentation.ui.activity.detailuser

import com.example.githubuserapp.data.response.DetailUsersResponse

sealed class DetailUserViewState {
    object Init: DetailUserViewState()
    data class Progress(val isLoading: Boolean): DetailUserViewState()
    data class ShowMessage(val message: String): DetailUserViewState()
    data class Favorite(val isFavorite: Boolean): DetailUserViewState()
    data class ShowDetailUser(val data: DetailUsersResponse): DetailUserViewState()
}
