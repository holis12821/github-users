package com.example.githubuserapp.presentation.ui.activity.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.githubuserapp.R
import com.example.githubuserapp.core.BaseActivity
import com.example.githubuserapp.data.response.DetailUsersResponse
import com.example.githubuserapp.databinding.ActivityFavoriteBinding
import com.example.githubuserapp.external.constant.KEY_EXTRA_FAVORITE_USERS
import com.example.githubuserapp.external.constant.KEY_EXTRA_USERS
import com.example.githubuserapp.external.extension.setUpVerticalLayoutManager
import com.example.githubuserapp.external.extension.viewGone
import com.example.githubuserapp.external.extension.viewVisible
import com.example.githubuserapp.presentation.ui.activity.detailuser.DetailUserActivity
import com.example.githubuserapp.presentation.ui.activity.detailuser.detail_favorite.DetailFavoriteActivity
import com.example.githubuserapp.presentation.ui.adapter.callback.AdapterClickListener
import com.example.githubuserapp.presentation.ui.adapter.FavoriteAdapter
import com.example.githubuserapp.presentation.ui.custom.NavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteActivity : BaseActivity<ActivityFavoriteBinding>() {

    private val viewModel by viewModel<FavoriteViewModel>()

    private lateinit var navigationView: NavigationView

    private val adapter = FavoriteAdapter().apply {
        listener = object : AdapterClickListener<DetailUsersResponse?> {

            override fun onItemClickCallback(data: DetailUsersResponse?) {
                val intent = Intent(this@FavoriteActivity, DetailFavoriteActivity::class.java)
                intent.putExtra(KEY_EXTRA_FAVORITE_USERS, data)
                startActivity(intent)
            }

            override fun onViewClickCallback(view: View, data: DetailUsersResponse?) {

            }

        }
    }

    override fun getResLayoutId(): Int = R.layout.activity_favorite

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        initView()
        onObserverState()
    }

    private fun initView() {
        binding.data = viewModel
        setUpNavigationView()
        setUpAdapter()
    }

    private fun setUpAdapter() {
        binding.rvFavorite.setUpVerticalLayoutManager()
        binding.rvFavorite.adapter = adapter
        binding.rvFavorite.setHasFixedSize(true)
    }

    private fun setUpNavigationView() {
        navigationView = NavigationView(this)
            .setOnBackPressedIcon(R.drawable.ic_baseline_arrow_back_ios_24)
            .setupTitle(resources.getString(R.string.favorite))
            .setNavigation { view ->
                when (view.id) {
                    R.id.navigation_back -> {
                        onBackPressed()
                    }
                }
            }
    }

    private fun onObserverState() {
        viewModel.stateData.observe(this, { state ->
            handleState(state = state)
        })
    }

    private fun handleState(state: FavoriteViewState) {
        when (state) {
            is FavoriteViewState.Init -> onInitState()
            is FavoriteViewState.Progress -> onProgress(state.isLoading)
            is FavoriteViewState.ShowMessage -> onShowMessage(state.message)
            is FavoriteViewState.ShowDataFavorite -> onShowFavorite(state.list)
        }
    }


    private fun onInitState() {
        binding.nestedScrollView.viewGone = true
    }

    private fun onProgress(loading: Boolean) {
        if (loading) showDialogProgress() else hideProgress()
    }

    private fun onShowMessage(message: String) {
        showToastDanger(this) { message }
    }

    private fun onShowFavorite(list: List<DetailUsersResponse>?) {
        if (list.isNullOrEmpty()) {
            binding.nestedScrollView.viewGone = true
            binding.rvFavorite.viewGone = true
            //layout Empty state
        } else {
            binding.nestedScrollView.viewVisible = true
            binding.rvFavorite.viewVisible = true
            adapter.setData(list = list)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        hideProgress()
    }
}