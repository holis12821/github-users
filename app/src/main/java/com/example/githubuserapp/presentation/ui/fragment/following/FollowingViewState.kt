package com.example.githubuserapp.presentation.ui.fragment.following

import com.example.githubuserapp.data.response.model.ItemsItem

sealed class FollowingViewState {
    object Init : FollowingViewState()
    data class Progress(val isLoading: Boolean) : FollowingViewState()
    data class ShowMessage(val message: String) : FollowingViewState()
    data class ShowFollowing(val list: List<ItemsItem>?) : FollowingViewState()
}
