/**
 * Github Users Apps
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 25/09/21 04.55 PM
 * Last modified 15/09/21 13.00 PM by Nurholis*/
package com.example.githubuserapp.presentation.ui.fragment.following

import com.example.githubuserapp.R
import com.example.githubuserapp.core.BaseFragment
import com.example.githubuserapp.data.response.ItemsItem
import com.example.githubuserapp.databinding.FragmentFollowingBinding
import com.example.githubuserapp.external.constant.KEY_EXTRA_USERS
import com.example.githubuserapp.external.extension.setUpVerticalLayoutManager
import com.example.githubuserapp.external.extension.viewGone
import com.example.githubuserapp.external.extension.viewVisible
import com.example.githubuserapp.presentation.ui.adapter.AdapterFollow
import org.koin.androidx.viewmodel.ext.android.viewModel

class FollowingFragments: BaseFragment<FragmentFollowingBinding>() {
    //init field and inject viewModel
    private val viewModel by viewModel<FollowingFragmentsViewModel>()
    private val adapter = AdapterFollow()

    override fun getResLayoutId(): Int = R.layout.fragment_following

    override fun onViewCreated() {
        initView()
        onObserver()
    }

    private fun initView() {
        setUpAdapter()
        val username = arguments?.getParcelable<ItemsItem>(KEY_EXTRA_USERS)
        viewModel.getFollowing(username?.login ?: "")
    }

    private fun onObserver() {
        viewModel.isLoading.observe(viewLifecycleOwner, { onProgress -> onProgress(onProgress) })
        viewModel.onError.observe(viewLifecycleOwner, { onShowMessage -> onShowMessage(onShowMessage) })
        viewModel.onSuccess.observe(viewLifecycleOwner, { onSuccess -> onSuccess(onSuccess) })
    }

    private fun setUpAdapter() {
        //set LayoutManager that this recyclerView will use
        binding?.rvList?.setUpVerticalLayoutManager()
        binding?.rvList?.adapter = adapter
        binding?.rvList?.setHasFixedSize(true)
    }

    private fun onProgress(loading: Boolean) {
        if (loading) {
            showDialogProgress()
        } else {
            hideProgress()
        }
    }

    private fun onShowMessage(message: Throwable) {
        activity?.let {
            showToastDanger(it) {message.message ?: ""}
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
    }
}