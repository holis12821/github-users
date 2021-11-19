/**
 * Github Users Apps
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 25/09/21 04:55 PM
 * Last modified 02/10/21 11:21 PM by Nurholis*/
package com.example.githubuserapp.presentation.ui.fragment.home

import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserapp.R
import com.example.githubuserapp.core.BaseFragment
import com.example.githubuserapp.data.response.model.ItemsItem
import com.example.githubuserapp.databinding.FragmentHomeBinding
import com.example.githubuserapp.external.extension.viewGone
import com.example.githubuserapp.external.extension.viewVisible
import com.example.githubuserapp.presentation.ui.activity.main.MainActivity
import com.example.githubuserapp.presentation.ui.adapter.UsersAdapter
import com.example.githubuserapp.presentation.ui.adapter.callback.AdapterClickListener
import com.example.githubuserapp.presentation.ui.custom.NavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment: BaseFragment<FragmentHomeBinding>(){

    //inject view Model in MainActivity using delegate property
    private val viewModel by viewModel<HomeFragmentViewModel>()

    private var query: String = ""

    //setup adapter
    private val adapter = UsersAdapter(this@HomeFragment).apply {
        listener = object : AdapterClickListener<ItemsItem> {
            override fun onItemClickCallback(data: ItemsItem, fragment: Fragment) {
                 if (fragment is HomeFragment) {
                     fragment.usersDetails(itemItems = data)
                 }
            }

            override fun onViewClickCallback(view: View, data: ItemsItem, fragment: Fragment) {

            }

        }
    }

    override fun getResLayoutId(): Int = R.layout.fragment_home

    override fun onViewCreated() {
       initView()
        onObserver()
    }

    private fun initView() {
        //init viewModel and data binding
        binding?.viewModel = viewModel
        binding?.btnSearch?.setOnClickListener {
            searchUsers()
        }
        //set edt to handle search
        binding?.edtTxtQuery?.setOnKeyListener { _, keyCode, keyEvent ->
            if (keyEvent.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                searchUsers()
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }

        //setUp Adapter
        setAdapter()

        binding?.layoutEmptyData?.viewVisible = true
    }

    private fun onObserver() {
        viewModel.stateData.observe(viewLifecycleOwner, { state ->
            handleState(state)
        })
    }

    private fun handleState(state: HomeViewState) {
        when (state) {
            is HomeViewState.Init -> onInitState()
            is HomeViewState.Progress -> onProgress(state.isLoading)
            is HomeViewState.ShowMessage -> onShowMessage(state.message)
            is HomeViewState.ShowSearchUsers -> onSuccess(state.list)
        }
    }

    private fun setAdapter() {
        //set LayoutManager that this recyclerView will use
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding?.rvListUsers?.layoutManager = linearLayoutManager
        binding?.rvListUsers?.adapter = adapter
        binding?.rvListUsers?.setHasFixedSize(true)
    }

    private fun searchUsers() {
        binding?.apply {
            query = edtTxtQuery.text.toString()
            if (query.isEmpty()) {
                showToastDanger(requireActivity()) {
                    resources.getString(R.string.toast_danger)
                }
                return
            }
            viewModel?.getSearchUsers(query)
        }
    }

    private fun onInitState() {
        binding?.rvListUsers?.viewGone = true
    }

    private fun onProgress(loading: Boolean) {
        if (loading) {
            showDialogProgress()
        } else {
            hideProgress()
        }
    }

    private fun onSuccess(list: List<ItemsItem>?) {
        if (list.isNullOrEmpty()) {
            binding?.layoutEmptyData?.viewVisible = false
            binding?.rvListUsers?.viewVisible = false
            binding?.layoutSearchNotFound?.viewVisible = true
            binding?.layoutNoInternet?.viewVisible = false
        } else {
            binding?.layoutSearchNotFound?.viewVisible = false
            binding?.layoutNoInternet?.viewVisible = false
            binding?.layoutEmptyData?.viewVisible = false
            binding?.rvListUsers?.viewVisible = true
            adapter.setData(list)
        }
    }

    private fun onShowMessage(message: String?) {
        showToastDanger(requireActivity()) { message ?: "" }
        binding?.layoutNoInternet?.viewVisible = true
        binding?.rvListUsers?.viewVisible = false
        binding?.layoutSearchNotFound?.viewVisible = false
        binding?.layoutEmptyData?.viewVisible = false
    }

    override fun onResume() {
        super.onResume()
        if (requireActivity() is MainActivity) {
            (activity as MainActivity?)?.showBottomNavigationView()
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

        findNavController().navigate(HomeFragmentDirections.actionHomeToDetailUsers(itemItems))
    }
}