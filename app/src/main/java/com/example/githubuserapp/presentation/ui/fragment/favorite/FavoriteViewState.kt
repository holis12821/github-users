package com.example.githubuserapp.presentation.ui.fragment.favorite

import com.example.githubuserapp.data.response.model.ItemsItem

sealed class FavoriteViewState {
    object Init: FavoriteViewState()
    data class Progress(val isLoading: Boolean): FavoriteViewState()
    data class ShowMessage(val message: String): FavoriteViewState()
    data class ShowDataFavorite(val list: List<ItemsItem>?): FavoriteViewState()
}