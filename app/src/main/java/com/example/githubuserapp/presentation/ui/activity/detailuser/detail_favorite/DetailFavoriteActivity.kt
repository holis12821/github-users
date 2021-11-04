package com.example.githubuserapp.presentation.ui.activity.detailuser.detail_favorite

import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.githubuserapp.R
import com.example.githubuserapp.core.BaseActivity
import com.example.githubuserapp.data.response.DetailUsersResponse
import com.example.githubuserapp.data.response.model.ItemsItem
import com.example.githubuserapp.databinding.ActivityDetailFavoriteBinding
import com.example.githubuserapp.external.constant.KEY_EXTRA_FAVORITE_USERS
import com.example.githubuserapp.external.constant.TAB_TITLES_FRAGMENT
import com.example.githubuserapp.external.extension.viewGone
import com.example.githubuserapp.external.extension.viewVisible
import com.example.githubuserapp.presentation.ui.activity.settings.SettingsActivity
import com.example.githubuserapp.presentation.ui.adapter.ViewPagerAdapter
import com.example.githubuserapp.presentation.ui.custom.NavigationView
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFavoriteActivity : BaseActivity<ActivityDetailFavoriteBinding>() {

    private val viewModel by viewModel<DetailFavoriteViewModel>()

    private lateinit var navigationView: NavigationView

    override fun getResLayoutId(): Int = R.layout.activity_detail_favorite

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        initView()
        onObserver()
    }

    private fun initView() {
        //get data from previous activity user detail
        val getFavoriteExtra = intent.getParcelableExtra<DetailUsersResponse>(KEY_EXTRA_FAVORITE_USERS)
        //bind viewModel in data binding
        binding.viewModel = viewModel
        //get detail favorite
        viewModel.getFavoriteDetail(getFavoriteExtra?.login ?: "")
        setUpNavigationView()
    }

    private fun setUpNavigationView() {
        navigationView =
            NavigationView(this).setOnBackPressedIcon(R.drawable.ic_baseline_arrow_back_ios_24)
                .setupTitle(resources.getString(R.string.detail_favorite))
                .setupNavIcon(R.drawable.ic_baseline_settings_24_white)
                .setNavigation {
                    when (it.id) {
                        R.id.navigation_back -> {
                            onBackPressed()
                        }
                        R.id.icon_settings -> {
                            val intent = Intent(this, SettingsActivity::class.java)
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

    private fun handleState(state: DetailFavoriteViewState) {
        when(state) {
            is DetailFavoriteViewState.Init -> onInitState()
            is DetailFavoriteViewState.Progress -> onProgress(state.isLoading)
            is DetailFavoriteViewState.ShowMessage -> onShowMessage(state.message)
            is DetailFavoriteViewState.ShowFavoriteDetail -> onSuccess(state.data)
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
            Glide.with(this@DetailFavoriteActivity)
                .load(detailUsers?.avatarUrl)
                .into(ciProfile)
            tvName.text = resources.getString(R.string.name_users, detailUsers?.name ?: "-")
            tvEmail.text =
                resources.getString(R.string.email_name, detailUsers?.email ?: "-")
            tvLocation.text =
                resources.getString(R.string.location_users, detailUsers?.location ?: "-")
            tvCompany.text =
                resources.getString(R.string.company_users, detailUsers?.company ?: "-")

            tvCalculateRepo.text = detailUsers?.publicRepos.toString()
            tvCalculateFollowers.text = detailUsers?.followers.toString()
            tvCalculateFollowing.text = detailUsers?.following.toString()
        }
    }

    private fun onShowMessage(message: String?) {
        showToastDanger(this) { message ?: "" }
    }

    override fun onDestroy() {
        super.onDestroy()
        hideProgress()
    }
}