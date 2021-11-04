package com.example.githubuserapp.presentation.ui.activity.detailuser.detail_favorite

import com.example.githubuserapp.data.response.DetailUsersResponse
import com.example.githubuserapp.presentation.ui.activity.detailuser.DetailUserViewState

sealed class DetailFavoriteViewState {
    object Init: DetailFavoriteViewState()
    data class Progress(val isLoading: Boolean): DetailFavoriteViewState()
    data class ShowMessage(val message: String): DetailFavoriteViewState()
    data class ShowFavoriteDetail(val data: DetailUsersResponse): DetailFavoriteViewState()
}