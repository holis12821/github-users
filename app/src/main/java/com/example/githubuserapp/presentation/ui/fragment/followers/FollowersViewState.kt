package com.example.githubuserapp.presentation.ui.fragment.followers

import com.example.githubuserapp.data.response.model.ItemsItem

sealed class FollowersViewState {
    object Init: FollowersViewState()
    data class Progress(val isLoading: Boolean): FollowersViewState()
    data class ShowMessage(val message: String): FollowersViewState()
    data class ShowFollowers(val list: List<ItemsItem>?): FollowersViewState()
}
