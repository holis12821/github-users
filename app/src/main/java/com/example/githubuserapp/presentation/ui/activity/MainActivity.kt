/**
 * Github Users Apps
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 15/09/21 01:00 PM
 * Last modified 15/09/21 01:00 PM by Nurholis*/
package com.example.githubuserapp.presentation.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserapp.R
import com.example.githubuserapp.core.BaseActivity
import com.example.githubuserapp.data.response.ItemsItem
import com.example.githubuserapp.data.response.UsersResponse
import com.example.githubuserapp.databinding.ActivityMainBinding
import com.example.githubuserapp.external.constant.KEY_EXTRA_USERS
import com.example.githubuserapp.external.extension.*
import com.example.githubuserapp.presentation.ui.activity.detailuser.DetailUserActivity
import com.example.githubuserapp.presentation.ui.activity.settings.SettingsActivity
import com.example.githubuserapp.presentation.ui.adapter.AdapterClickListener
import com.example.githubuserapp.presentation.ui.adapter.UsersAdapter
import com.example.githubuserapp.presentation.ui.custom.NavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>() {
    //inject view Model in MainActivity using delegate property
    private val viewModel by viewModel<MainViewModel>()
    //initialization view
    private lateinit var navigationView: NavigationView

    private lateinit var query: String

    override fun getResLayoutId(): Int = R.layout.activity_main

   private val  adapter = UsersAdapter().apply {
        listener = object : AdapterClickListener<ItemsItem?> {
            override fun onItemClickCallback(data: ItemsItem?) {
                val intent = Intent(this@MainActivity, DetailUserActivity::class.java)
                intent.putExtra(KEY_EXTRA_USERS, data)
                startActivity(intent)
            }

            override fun onViewClickCallback(view: View, data: ItemsItem?) {
                when(view.id) {
                    R.id.btn_add_favorite -> {
                        //add to local database

                    }
                }
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        initView()
        onObserver()
    }

    private fun initView() {
        //init viewModel and data binding
        binding.viewModel = viewModel

        setUpNavigationView()

        binding.btnSearch.setOnClickListener {
            searchUsers()
        }

        //set edt to handle search
        binding.edtTxtQuery.setOnKeyListener { _, keyCode, keyEvent ->
            if (keyEvent.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                searchUsers()
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }

        //setUp Adapter
        setAdapter()

        binding.layoutEmptyData.viewVisible = true
    }

    private fun onObserver() {
        viewModel.isLoading.observe(this, { onLoading -> onProgress(onLoading) })
        viewModel.onError.observe(this, { isThrowable -> onShowMessage(isThrowable)})
        viewModel.onSuccess.observe(this, { usersResponse -> onSuccess(usersResponse)})
    }

    private fun setUpNavigationView(){
        navigationView = NavigationView(this)
            .setupTitle(resources.getString(R.string.github_title_navbar))
            .setupNavIcon(R.drawable.ic_baseline_settings_24)
            .setNavMoreIcon(R.drawable.ic_baseline_favorite_24_white)
            .setNavigation { view ->
                //set up local favorite users
                when(view.id) {
                    //create callback for icon in navBar
                    R.id.icon_settings -> {
                        //setting
                        val intent = Intent(this, SettingsActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    R.id.icon_favorite -> {
                        //favorite
                    }
                }
            }
    }

    private fun setAdapter() {
        //set LayoutManager that this recyclerView will use
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvListUsers.layoutManager = linearLayoutManager
        binding.rvListUsers.adapter = adapter
        binding.rvListUsers.setHasFixedSize(true)
    }

    private fun searchUsers() {
        binding.apply {
            query = edtTxtQuery.text.toString()
            if (query.isEmpty()) {
                showToastDanger(this@MainActivity) { resources.getString(R.string.toast_danger) }
                return
            }
            viewModel?.getSearchUsers(query)
        }
    }

    private fun onProgress(loading: Boolean) {
        if (loading) {
            showDialogProgress()
        } else {
            hideProgress()
        }
    }

    private fun onSuccess(usersResponse: UsersResponse?) {
        if (usersResponse?.items.isNullOrEmpty() ) {
            binding.layoutEmptyData.viewVisible = false
            binding.rvListUsers.viewVisible = false
            binding.layoutSearchNotFound.viewVisible = true
        } else {
            binding.layoutSearchNotFound.viewVisible = false
            binding.layoutNoInternet.viewVisible = false
            binding.layoutEmptyData.viewVisible = false
            binding.rvListUsers.viewVisible = true
            adapter.setData(usersResponse?.items)
        }
    }

    private fun onShowMessage(message: Throwable) {
        showToastDanger(this) { message.message ?: "" }
        binding.layoutNoInternet.viewVisible = true
        binding.rvListUsers.viewVisible = false
        binding.layoutSearchNotFound.viewVisible = false
        binding.layoutEmptyData.viewVisible = false
    }
}