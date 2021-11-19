/**
 * Github Users Apps
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 25/09/21 04:55 PM
 * Last modified 02/10/21 11:21 PM by Nurholis*/
package com.example.githubuserapp.presentation.ui.fragment.followers

import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.githubuserapp.R
import com.example.githubuserapp.core.BaseFragment
import com.example.githubuserapp.data.response.model.ItemsItem
import com.example.githubuserapp.databinding.FragmentFollowersBinding
import com.example.githubuserapp.external.constant.KEY_EXTRA_USERS
import com.example.githubuserapp.external.extension.setUpVerticalLayoutManager
import com.example.githubuserapp.external.extension.viewGone
import com.example.githubuserapp.external.extension.viewVisible
import com.example.githubuserapp.presentation.ui.activity.main.MainActivity
import com.example.githubuserapp.presentation.ui.adapter.UsersAdapter
import com.example.githubuserapp.presentation.ui.adapter.callback.AdapterClickListener
import com.example.githubuserapp.presentation.ui.fragment.home.HomeFragmentDirections
import org.koin.androidx.viewmodel.ext.android.viewModel

class FollowersFragments : BaseFragment<FragmentFollowersBinding>() {
    //init field on this class and inject viewModel
    private val viewModel by viewModel<FollowersFragmentsViewModel>()
    private val adapter = UsersAdapter(this@FollowersFragments).apply {
        listener = object : AdapterClickListener<ItemsItem> {
            override fun onItemClickCallback(data: ItemsItem, fragment: Fragment) {
                if (fragment is FollowersFragments) {
                    fragment.usersDetails(data)
                }
            }

            override fun onViewClickCallback(view: View, data: ItemsItem, fragment: Fragment) {
                TODO("Not yet implemented")
            }

        }
    }

    override fun getResLayoutId(): Int = R.layout.fragment_followers

    override fun onViewCreated() {
        initView()
        onObserver()
    }

    private fun initView() {
        setUpAdapter()
        val username = arguments?.getParcelable<ItemsItem>(KEY_EXTRA_USERS) as ItemsItem
        viewModel.getFollowers(username = username.login ?: "")
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
        binding?.rvList?.viewGone = true
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
            showToastDanger(it) { message ?: "" }
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

    /**
     * A function to navigate to the Users Details Fragment.
     *
     * @param itemItems
     */
    fun usersDetails(itemItems: ItemsItem) {
        if (requireActivity() is MainActivity) {
            (activity as MainActivity?)?.hideBottomNavigationView()
        }

        findNavController().navigate(FollowersFragmentsDirections.actionFollowersUsersToDetailUsers(itemsItem = itemItems))
    }
}