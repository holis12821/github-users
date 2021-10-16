/**
 * Github Users Apps
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 15/09/21 01:00 PM
 * Last modified 15/09/21 01:00 PM by Nurholis*/
package com.example.githubuserapp.presentation.ui.custom

import android.app.Activity
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.example.githubuserapp.R

/**
 * This class to make custom Navigation View such as back menu and title
 * in this application*/
class NavigationView (activity: Activity, var view: View? = null) {
    init {
        view = activity.window.decorView.rootView
    }

    fun setupNavIcon(icon: Int): NavigationView {
        val navigationSettingsIcon = view?.findViewById<AppCompatImageView?>(R.id.icon_settings)
        navigationSettingsIcon?.apply {
            setImageResource(icon)
        }
        return this
    }

    fun setNavMoreIcon(icon: Int): NavigationView {
        val navigationFavIcon = view?.findViewById<AppCompatImageView>(R.id.icon_favorite)
        navigationFavIcon?.apply {
            setImageResource(icon)
        }
        return this
    }

    fun setOnBackPressedIcon(icon: Int): NavigationView {
        val navigationBackIcon = view?.findViewById<AppCompatImageView>(R.id.navigation_back)
        navigationBackIcon?.apply {
            setImageResource(icon)
        }
        return this
    }

    fun setupTitle(title: String?): NavigationView {
        val navigationTitle = view?.findViewById<AppCompatTextView?>(R.id.navigation_title)
        navigationTitle?.let {
            if (title.isNullOrEmpty()) return@let
            else it.text = title
        }
        return this
    }

    fun setNavigation(callback: (View) -> Unit): NavigationView {
        val navigationCallBack = view?.findViewById<AppCompatImageView>(R.id.navigation_back)
        val navigationIconSettings = view?.findViewById<AppCompatImageView>(R.id.icon_settings)
        val navigationIconFav = view?.findViewById<AppCompatImageView>(R.id.icon_favorite)
        navigationCallBack?.setOnClickListener { navCallBack ->
            callback.invoke(navCallBack)
        }
        navigationIconSettings?.setOnClickListener { settingsCallBack ->
            callback.invoke(settingsCallBack)
        }
        navigationIconFav?.setOnClickListener { settingsFavCallBack ->
            callback.invoke(settingsFavCallBack)
        }
        return this
    }
}