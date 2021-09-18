/**
 * Github Users Apps
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 15/09/21 13.00 PM
 * Last modified 15/09/21 13.00 PM by Nurholis*/
package com.example.githubuserapp.presentation.ui.activity.detailuser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.githubuserapp.R
import com.example.githubuserapp.core.BaseActivity
import com.example.githubuserapp.data.response.ItemsItem
import com.example.githubuserapp.databinding.ActivityDetailUserBinding
import com.example.githubuserapp.presentation.ui.custom.NavigationView

class DetailUserActivity: BaseActivity<ActivityDetailUserBinding>() {

    private lateinit var navigationView: NavigationView

    override fun getResLayoutId(): Int = R.layout.activity_detail_user

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        initView()
        setUpNavigationView()
    }

    private fun initView() {
        val getUsersExtra = intent.getParcelableExtra<ItemsItem>(KEY_EXTRA_USERS) as ItemsItem
        //load image in glide
        /*Glide.with(this)
            .load(getUsersExtra.avatar)
            .into(binding.ciProfile)
        //bind value in the textView
        binding.tvUsername.text = "${getUsersExtra.username}"
        binding.tvName.text = "${getUsersExtra.name}"
        binding.tvLocation.text = "${getUsersExtra.location}"
        binding.tvCompany.text = "${getUsersExtra.company}"
        binding.tvCalculateRepo.text = "${getUsersExtra.repository}"
        binding.tvCalculateFollowers.text = "${getUsersExtra.follower}"
        binding.tvCalculateFollowing.text = "${getUsersExtra.following}"*/
    }

    private fun setUpNavigationView() {
        navigationView = NavigationView(this).setupNavIcon(R.drawable.ic_baseline_arrow_back_ios_24)
            .setupTitle("Detail Users")
            .setNavigation {
                onBackPressed()
            }
    }

    companion object {
        const val KEY_EXTRA_USERS = "extra_users"
    }
}