/**
 * Github Users Apps
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 15/09/21 13.00 PM
 * Last modified 15/09/21 13.00 PM by Nurholis*/
package com.example.githubuserapp.presentation.ui.fragment.detailusers

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.githubuserapp.R
import com.example.githubuserapp.core.BaseFragment
import com.example.githubuserapp.data.response.model.ItemsItem
import com.example.githubuserapp.databinding.FragmentDetailBinding
import com.example.githubuserapp.external.constant.KEY_EXTRA_USERS
import com.example.githubuserapp.external.constant.TAB_TITLES_FRAGMENT
import com.example.githubuserapp.external.extension.viewGone
import com.example.githubuserapp.external.extension.viewVisible
import com.example.githubuserapp.presentation.ui.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    private val viewModel by viewModel<DetailFragmentViewModel>()

    //get data from previous fragment home
    private val args: DetailFragmentArgs by navArgs()

    //setup view pager
    private lateinit var viewPagerAdapter: ViewPagerAdapter


    override fun getResLayoutId(): Int = R.layout.fragment_detail

    override fun onViewCreated() {
        //to do code in initiate here
        initView()
        onObserver()
    }

    private fun initView() {
        //bind viewModel in data binding
        binding?.viewModel = viewModel
        viewModel.getDetailUsers(args.itemsItem.login ?: "")
        //define bundle
        val mBundle = Bundle()
        mBundle.putParcelable(KEY_EXTRA_USERS, args.itemsItem)
        setUpAdapterViewPager(mBundle = mBundle)

        setUpFavorite()
        setUpClickableFavorite()

    }

    private fun setUpAdapterViewPager(mBundle: Bundle) {
        viewPagerAdapter = ViewPagerAdapter(requireActivity(), mBundle = mBundle)
        binding?.apply {
            viewPager.adapter = viewPagerAdapter
            TabLayoutMediator(tabsLayout, viewPager) { tabsLayout, position ->
                tabsLayout.text = resources.getString(TAB_TITLES_FRAGMENT[position])
            }.attach()
        }
    }

    private fun onObserver() {
        viewModel.stateData.observe(viewLifecycleOwner, { state ->
            handleState(state)
        })
    }

    private fun handleState(state: DetailUsersViewState) {
        when (state) {
            is DetailUsersViewState.Init -> onInitState()
            is DetailUsersViewState.Progress -> onProgress(state.isLoading)
            is DetailUsersViewState.ShowMessage -> onShowMessage(state.message)
            is DetailUsersViewState.ShowDetailUser -> onSuccess(state.data)
        }
    }

    private fun onInitState() {
        binding?.ivFavorite?.viewGone = true
    }

    private fun onProgress(loading: Boolean) {
        if (loading) {
            showDialogProgress()
        } else {
            hideProgress()
        }
    }

    private fun onSuccess(itemsItem: ItemsItem?) {
        binding?.ivFavorite?.viewVisible = true
        //binding data to view
        binding?.apply {
            //load image view Glide
            Glide.with(requireActivity())
                .load(itemsItem?.avatarUrl)
                .into(ciProfile)
            tvName.text = resources.getString(R.string.name_users, itemsItem?.name ?: "-")
            tvEmail.text =
                resources.getString(R.string.email_name, itemsItem?.email ?: "-")
            tvLocation.text =
                resources.getString(R.string.location_users, itemsItem?.location ?: "-")
            tvCompany.text =
                resources.getString(R.string.company_users, itemsItem?.company ?: "-")

            tvCalculateRepo.text = itemsItem?.publicRepos.toString()
            tvCalculateFollowers.text = itemsItem?.followers.toString()
            tvCalculateFollowing.text = itemsItem?.following.toString()
        }
    }

    private fun setUpFavorite() {
        //setup favorite if isFavorite true indicated favorite saved otherwise no
        if (args.itemsItem.isFavorite) {
            binding?.ivFavorite?.setImageDrawable(
                ContextCompat.getDrawable(
                    requireActivity(),
                    R.drawable.ic_baseline_favorite_24_red
                )
            )
        } else {
            binding?.ivFavorite?.setImageDrawable(
                ContextCompat.getDrawable(
                    requireActivity(),
                    R.drawable.ic_baseline_favorite_border_24
                )
            )
        }
    }

    private fun setUpClickableFavorite() {
        binding?.ivFavorite?.setOnClickListener {
            args.itemsItem.isFavorite = !args.itemsItem.isFavorite
            //update data from negation isFavorite
            viewModel.updateAsFavorite(args.itemsItem)

            if (args.itemsItem.isFavorite) {
                viewModel.saveAsFavorite(args.itemsItem)
                binding?.ivFavorite?.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireActivity(),
                        R.drawable.ic_baseline_favorite_24_red
                    )
                )
                showPositiveToast(requireActivity()) { resources.getString(R.string.save_data_to_database) }
            } else {
                viewModel.removeAsFavorite(args.itemsItem)
                binding?.ivFavorite?.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireActivity(),
                        R.drawable.ic_baseline_favorite_border_24
                    )
                )
                showPositiveToast(requireActivity()) { resources.getString(R.string.delete_data_to_database) }
            }
        }
    }

    private fun onShowMessage(message: String?) {
        showToastDanger(requireActivity()) { message ?: "" }
    }
}