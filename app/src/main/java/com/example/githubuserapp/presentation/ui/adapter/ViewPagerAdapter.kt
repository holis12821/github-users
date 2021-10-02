/**
 * Github Users Apps
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 25/09/21 04.55 PM
 * Last modified 15/09/21 13.00 PM by Nurholis*/
package com.example.githubuserapp.presentation.ui.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.githubuserapp.external.constant.TAB_TITLES_FRAGMENT
import com.example.githubuserapp.presentation.ui.activity.detailuser.DetailUserActivity
import com.example.githubuserapp.presentation.ui.fragment.followers.FollowersFragments
import com.example.githubuserapp.presentation.ui.fragment.following.FollowingFragments

class ViewPagerAdapter(
    mFragmentActivity: FragmentActivity,
    mBundle: Bundle?
): FragmentStateAdapter(mFragmentActivity) {

    private var mBundleFragment: Bundle? = null

    init {
        mBundleFragment = mBundle
    }

    override fun getItemCount(): Int {
     return  TAB_TITLES_FRAGMENT.size
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FollowersFragments()
            1 -> fragment = FollowingFragments()
        }
        fragment?.arguments = this.mBundleFragment
        return fragment as Fragment
    }
}