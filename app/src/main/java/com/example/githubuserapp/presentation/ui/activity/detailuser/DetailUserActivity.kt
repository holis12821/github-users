/**
 * Github Users Apps
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 15/09/21 13.00 PM
 * Last modified 15/09/21 13.00 PM by Nurholis*/
package com.example.githubuserapp.presentation.ui.activity.detailuser

import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.githubuserapp.R
import com.example.githubuserapp.core.BaseActivity
import com.example.githubuserapp.data.response.DetailUsersResponse
import com.example.githubuserapp.data.response.model.ItemsItem
import com.example.githubuserapp.databinding.ActivityDetailUserBinding
import com.example.githubuserapp.external.constant.KEY_EXTRA_USERS
import com.example.githubuserapp.external.constant.TAB_TITLES_FRAGMENT
import com.example.githubuserapp.external.extension.viewGone
import com.example.githubuserapp.external.extension.viewVisible
import com.example.githubuserapp.presentation.ui.activity.favorite.FavoriteActivity
import com.example.githubuserapp.presentation.ui.activity.settings.SettingsActivity
import com.example.githubuserapp.presentation.ui.adapter.ViewPagerAdapter
import com.example.githubuserapp.presentation.ui.custom.NavigationView
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailUserActivity: BaseActivity<ActivityDetailUserBinding>() {

    private val viewModel by viewModel<DetailUserViewModel>()

    private lateinit var navigationView: NavigationView
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    private var isFavorite = false

    override fun getResLayoutId(): Int = R.layout.activity_detail_user

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        initView()
        onObserver()
    }

    private fun initView() {
        binding.viewModel = viewModel
        //get data from previous activity
        val getUsersExtra = intent.getParcelableExtra<ItemsItem>(KEY_EXTRA_USERS) as ItemsItem
        getUsersExtra.login?.let { username ->
            viewModel.setUsername(username = username)
        }
        val mBundle = Bundle()
        mBundle.putParcelable(KEY_EXTRA_USERS, getUsersExtra)
        setUpNavigationView()
        //setUp Adapter to handle ViewPager2
        setUpAdapterViewPager(mBundle)

        binding.fabFavorite.setOnClickListener {
            onChangeFavorite(isFavorite)
            onAddOrRemoveFavorite(viewModel.getDetailUsers())
        }
    }

    private fun setUpAdapterViewPager(mBundle: Bundle) {
        viewPagerAdapter = ViewPagerAdapter(this, mBundle = mBundle)
        with(binding) {
            viewPager.adapter = viewPagerAdapter
            TabLayoutMediator(tabsLayout, viewPager) {tabsLayout, position ->
                tabsLayout.text = resources.getString(TAB_TITLES_FRAGMENT[position])
            }.attach()
        }
    }

    private fun setUpNavigationView() {
        navigationView = NavigationView(this).setOnBackPressedIcon(R.drawable.ic_baseline_arrow_back_ios_24)
            .setupTitle(resources.getString(R.string.detail_users))
            .setupNavIcon(R.drawable.ic_baseline_settings_24)
            .setNavMoreIcon(R.drawable.ic_baseline_favorite_24_white)
            .setNavigation {
               when (it.id) {
                   R.id.navigation_back -> {
                       onBackPressed()
                   }
                   R.id.icon_settings -> {
                       val intent = Intent(this, SettingsActivity::class.java)
                       startActivity(intent)
                   }
                   R.id.icon_favorite -> {
                       val intent = Intent(this, FavoriteActivity::class.java)
                       startActivity(intent)
                   }
               }
            }

    }

    private fun onObserver() {
       viewModel.stateData.observe(this, { state ->
           handleState(state)
       })
    }

    private fun handleState(state: DetailUserViewState) {
        when(state) {
            is DetailUserViewState.Init -> onInitState()
            is DetailUserViewState.Progress -> onProgress(state.isLoading)
            is DetailUserViewState.ShowMessage -> onShowMessage(state.message)
            is DetailUserViewState.Favorite -> onChangeFavorite(state.isFavorite)
            is DetailUserViewState.ShowDetailUser -> onSuccess(state.data)
        }
    }

    private fun onInitState() {
        binding.scrollView.viewGone = true
    }

    private fun onProgress(loading: Boolean) {
        if (loading) {
            showDialogProgress()
        } else {
            hideProgress()
        }
    }

    private fun onSuccess(detailUsers: DetailUsersResponse?) {
        binding.scrollView.viewVisible = true
        //binding data to view
        with(binding) {
            //load image view Glide
            Glide.with(this@DetailUserActivity)
                .load(detailUsers?.avatarUrl)
                .into(ciProfile)
            tvName.text = resources.getString(R.string.name_users, detailUsers?.name ?: "-")
            tvBio.text = resources.getString(R.string.bio_users, detailUsers?.bio ?: "-")
            tvLocation.text = resources.getString(R.string.location_users, detailUsers?.location ?: "-")
            tvCompany.text = resources.getString(R.string.company_users, detailUsers?.company ?: "-")

            tvCalculateRepo.text = detailUsers?.publicRepos.toString()
            tvCalculateFollowers.text = detailUsers?.followers.toString()
            tvCalculateFollowing.text = detailUsers?.following.toString()
        }
        detailUsers?.let {
            viewModel.init(it)
        }
    }

    private fun onShowMessage(message: String?) {
        showToastDanger(this) { message ?: ""}
    }

    private fun onChangeFavorite(isChange: Boolean) {
        isFavorite = isChange
        if (isChange) {
            binding.fabFavorite.setImageResource(R.drawable.ic_baseline_favorite_24_red)
        } else {
            binding.fabFavorite.setImageResource(R.drawable.ic_baseline_favorite_24_dark_grey)
        }
    }

    private fun onAddOrRemoveFavorite(detailUsers: DetailUsersResponse?) {
       if (isFavorite) {
           viewModel.saveAsFavorite(detailUsers)
           showPositiveToast(this) {resources.getString(R.string.save_data_to_database)}
       } else {
           viewModel.removeAsFavorite(detailUsers)
           showPositiveToast(this) {resources.getString(R.string.delete_data_to_database)}
       }
    }

    override fun onDestroy() {
        super.onDestroy()
        hideProgress()
    }
}