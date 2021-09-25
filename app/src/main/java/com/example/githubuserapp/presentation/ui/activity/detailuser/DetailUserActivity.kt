/**
 * Github Users Apps
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 15/09/21 13.00 PM
 * Last modified 15/09/21 13.00 PM by Nurholis*/
package com.example.githubuserapp.presentation.ui.activity.detailuser

import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.githubuserapp.R
import com.example.githubuserapp.core.BaseActivity
import com.example.githubuserapp.data.response.DetailUsersResponse
import com.example.githubuserapp.data.response.ItemsItem
import com.example.githubuserapp.databinding.ActivityDetailUserBinding
import com.example.githubuserapp.presentation.ui.custom.NavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailUserActivity: BaseActivity<ActivityDetailUserBinding>() {

    private val viewModel by viewModel<DetailUserViewModel>()

    private lateinit var navigationView: NavigationView

    override fun getResLayoutId(): Int = R.layout.activity_detail_user

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        initView()
        onObserver()
    }

    private fun initView() {
        setUpNavigationView()
        val getUsersExtra = intent.getParcelableExtra<ItemsItem>(KEY_EXTRA_USERS) as ItemsItem
        getUsersExtra.login?.let {
            viewModel.getDetailUsers(it)
        }
    }

    private fun onObserver() {
        viewModel.isLoading.observe(this, { onLoading -> onProgress(onLoading)} )
        viewModel.onError.observe(this, {onError -> onShowMessage(onError)} )
        viewModel.onSuccess.observe(this, { onSuccess -> onSuccess(onSuccess)} )

    }

    private fun setUpNavigationView() {
        navigationView = NavigationView(this).setupNavIcon(R.drawable.ic_baseline_arrow_back_ios_24)
            .setupTitle(resources.getString(R.string.detail_users))
            .setNavigation {
                onBackPressed()
            }
    }

    private fun onProgress(loading: Boolean) {
        if (loading) {
            showDialogProgress()
        } else {
            hideProgress()
        }
    }

    private fun onSuccess(detailUsersResponse: DetailUsersResponse?) {
        //load image view Glide
        Glide.with(this)
            .load(detailUsersResponse?.avatarUrl)
            .into(binding.ciProfile)
        //bind value in the TextView
        binding.tvUsername.text = detailUsersResponse?.login
        binding.tvName.text = detailUsersResponse?.name
        binding.tvLocation.text = detailUsersResponse?.location
        binding.tvCompany.text = detailUsersResponse?.company
        binding.tvCalculateRepo.text = detailUsersResponse?.publicRepos.toString()
        binding.tvCalculateFollowers.text = detailUsersResponse?.followers.toString()
        binding.tvCalculateFollowing.text = detailUsersResponse?.following.toString()
    }

    private fun onShowMessage(message: Throwable) {
        showToastDanger(this) { message.message ?: ""}

    }

    companion object {
        const val KEY_EXTRA_USERS = "extra_users"
    }
}