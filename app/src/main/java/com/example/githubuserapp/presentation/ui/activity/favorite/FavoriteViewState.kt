package com.example.githubuserapp.presentation.ui.activity.favorite

import com.example.githubuserapp.data.response.DetailUsersResponse

sealed class FavoriteViewState {
    object Init: FavoriteViewState()
    data class Progress(val isLoading: Boolean): FavoriteViewState()
    data class ShowMessage(val message: String): FavoriteViewState()
    data class ShowDataFavorite(val list: List<DetailUsersResponse>?): FavoriteViewState()
}