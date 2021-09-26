/**
 * Github Users Apps
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 15/09/21 13.00 PM
 * Last modified 15/09/21 13.00 PM by Nurholis*/
package com.example.githubuserapp.presentation.ui.activity.detailuser

import android.annotation.SuppressLint
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.githubuserapp.R
import com.example.githubuserapp.core.BaseActivity
import com.example.githubuserapp.data.response.DetailUsersResponse
import com.example.githubuserapp.data.response.ItemsItem
import com.example.githubuserapp.databinding.ActivityDetailUserBinding
import com.example.githubuserapp.external.constant.TAB_TITLES_FRAGMENT
import com.example.githubuserapp.external.extension.viewVisible
import com.example.githubuserapp.presentation.ui.adapter.ViewPagerAdapter
import com.example.githubuserapp.presentation.ui.custom.NavigationView
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailUserActivity: BaseActivity<ActivityDetailUserBinding>() {

    private val viewModel by viewModel<DetailUserViewModel>()

    private lateinit var navigationView: NavigationView
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var bundle: Bundle

    override fun getResLayoutId(): Int = R.layout.activity_detail_user

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        initView()
        onObserver()
    }

    private fun initView() {
        setUpNavigationView()
        //setUp Adapter to handle ViewPager2
        setUpAdapterViewPager()
        //get data from previous activity
        val getUsersExtra = intent.getParcelableExtra<ItemsItem>(KEY_EXTRA_USERS) as ItemsItem
        //send data to fragment followers and following
        bundle = Bundle()
        bundle.putString(KEY_EXTRA_USERS, getUsersExtra.login)
        getUsersExtra.login?.let {
            viewModel.getDetailUsers(it)
        }
    }

    private fun setUpAdapterViewPager() {
        viewPagerAdapter = ViewPagerAdapter(this, bundle)
        with(binding) {
            viewPager.adapter = viewPagerAdapter
            TabLayoutMediator(tabsLayout, viewPager) {tabsLayout, position ->
                tabsLayout.text = resources.getString(TAB_TITLES_FRAGMENT[position])
            }.attach()
        }
    }

    private fun setUpNavigationView() {
        navigationView = NavigationView(this).setupNavIcon(R.drawable.ic_baseline_arrow_back_ios_24)
            .setupTitle(resources.getString(R.string.detail_users))
            .setNavigation {
                onBackPressed()
            }
    }

    private fun onObserver() {
        viewModel.isLoading.observe(this, { onLoading -> onProgress(onLoading)} )
        viewModel.onError.observe(this, {onError -> onShowMessage(onError)} )
        viewModel.onSuccess.observe(this, { onSuccess -> onSuccess(onSuccess)} )

    }

    private fun onProgress(loading: Boolean) {
        if (loading) {
            showDialogProgress()
        } else {
            hideProgress()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun onSuccess(detailUsersResponse: DetailUsersResponse?) {

        //binding data to view
        with(binding) {
            //load image view Glide
            Glide.with(this@DetailUserActivity)
                .load(detailUsersResponse?.avatarUrl)
                .into(ciProfile)
            tvUsername.text = "Username : ${detailUsersResponse?.login ?: "-"}"
            tvName.text = "Name : ${detailUsersResponse?.name ?: "-"}"
            tvLocation.text = "Location : ${detailUsersResponse?.location ?: "-"}"
            tvCompany.text = "Company : ${detailUsersResponse?.company ?: "-"}"

            tvCalculateRepo.text = detailUsersResponse?.publicRepos.toString()
            tvCalculateFollowers.text = detailUsersResponse?.followers.toString()
            tvCalculateFollowing.text = detailUsersResponse?.following.toString()
        }

    }

    private fun onShowMessage(message: Throwable) {
        showToastDanger(this) { message.message ?: ""}

    }

    companion object {
        const val KEY_EXTRA_USERS = "extra_users"
    }
}