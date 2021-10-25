/**
 * Github Users Apps
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 25/09/21 04:55 PM
 * Last modified 02/10/21 11:21 PM by Nurholis*/
package com.example.githubuserapp.presentation.ui.fragment.followers

import com.example.githubuserapp.R
import com.example.githubuserapp.core.BaseFragment
import com.example.githubuserapp.data.response.model.ItemsItem
import com.example.githubuserapp.databinding.FragmentFollowersBinding
import com.example.githubuserapp.external.constant.KEY_EXTRA_USERS
import com.example.githubuserapp.external.extension.setUpVerticalLayoutManager
import com.example.githubuserapp.external.extension.viewGone
import com.example.githubuserapp.external.extension.viewVisible
import com.example.githubuserapp.presentation.ui.adapter.AdapterFollow
import org.koin.androidx.viewmodel.ext.android.viewModel

class FollowersFragments: BaseFragment<FragmentFollowersBinding>() {
    //init field on this class and inject viewModel
    private val viewModel by viewModel<FollowersFragmentsViewModel>()
    private val adapter =  AdapterFollow()

    override fun getResLayoutId(): Int = R.layout.fragment_followers

    override fun onViewCreated() {
       initView()
       onObserver()
    }

    private fun initView() {
        setUpAdapter()
        val username = arguments?.getParcelable<ItemsItem>(KEY_EXTRA_USERS)
        viewModel.setUsername(username = username?.login ?: "")
    }

    private fun onObserver() {
        viewModel.stateData.observe(viewLifecycleOwner, { state ->
            handleState(state)
        })
    }

    private fun handleState(state: FollowersViewState) {
        when (state) {
            is FollowersViewState.Init -> onInitState()
            is FollowersViewState.Progress -> onProgress(state.isLoading)
            is FollowersViewState.ShowMessage -> onShowMessage(state.message)
            is FollowersViewState.ShowFollowers -> onSuccess(state.list)
        }
    }

    private fun setUpAdapter() {
        //set LayoutManager that this recyclerView will use
        binding?.rvList?.setUpVerticalLayoutManager()
        binding?.rvList?.adapter = adapter
        binding?.rvList?.setHasFixedSize(true)
    }

    private fun onInitState() {
        binding?.rvList?.viewGone= true
    }

    private fun onProgress(loading: Boolean) {
        if (loading) {
            showDialogProgress()
        } else {
            hideProgress()
        }
    }

    private fun onShowMessage(message: String?) {
        activity?.let {
            showToastDanger(it) { message ?: ""}
        }
        binding?.layoutEmptyData?.viewVisible = true
        binding?.rvList?.viewVisible = false
    }

    private fun onSuccess(listUsers: List<ItemsItem>?) {
        if (listUsers.isNullOrEmpty()) {
            binding?.rvList?.viewGone = true
            binding?.layoutEmptyData?.viewVisible = true
        } else {
            binding?.layoutEmptyData?.viewGone = true
            binding?.rvList?.viewVisible = true
            adapter.setData(listUsers)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        hideProgress()
    }
}