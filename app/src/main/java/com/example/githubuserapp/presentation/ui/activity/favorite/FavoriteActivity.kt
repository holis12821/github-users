package com.example.githubuserapp.presentation.ui.activity.favorite

import android.os.Bundle
import androidx.core.view.isGone
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserapp.R
import com.example.githubuserapp.core.BaseActivity
import com.example.githubuserapp.data.response.DetailUsersResponse
import com.example.githubuserapp.databinding.ActivityFavoriteBinding
import com.example.githubuserapp.external.extension.setUpLinearLayoutManager
import com.example.githubuserapp.external.extension.setUpVerticalLayoutManager
import com.example.githubuserapp.external.extension.viewGone
import com.example.githubuserapp.external.extension.viewVisible
import com.example.githubuserapp.presentation.ui.adapter.FavoriteAdapter
import com.example.githubuserapp.presentation.ui.custom.NavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteActivity: BaseActivity<ActivityFavoriteBinding>() {

    private val viewModel by viewModel<FavoriteViewModel>()

    private lateinit var navigationView: NavigationView

    private lateinit var adapter: FavoriteAdapter

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
        adapter = FavoriteAdapter()
        binding.rvFavorite.setUpVerticalLayoutManager()
        binding.rvFavorite.adapter = adapter
        binding.rvFavorite.setHasFixedSize(true)
    }

    private fun setUpNavigationView() {
        navigationView = NavigationView(this)
            .setOnBackPressedIcon(R.drawable.ic_baseline_arrow_back_ios_24)
            .setupTitle(resources.getString(R.string.favorite))
            .setNavigation { view ->
                when(view.id) {
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
        when(state) {
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