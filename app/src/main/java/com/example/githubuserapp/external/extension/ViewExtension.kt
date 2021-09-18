/**
 * Github Users Apps
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 15/09/21 13:00 PM
 * Last modified 15/09/21 13:00 PM by Nurholis*/
package com.example.githubuserapp.external.extension

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

/**
  * This ViewExtension contains general extension
  * properties and methods.
  * */

 /**
  * This extension properties is common properties or variable
  * */
inline var View.viewGone: Boolean
    get() = visibility == View.GONE
    set(value) {
        visibility = if (value) View.GONE else View.VISIBLE
    }

inline var View.viewVisible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }

fun SwipeRefreshLayout.swipeVisible() {
    isRefreshing = true
}

fun SwipeRefreshLayout.swipeGone() {
    isRefreshing = false
}

fun RecyclerView.setUpLinearLayoutManager(@RecyclerView.Orientation orientation: Int) {
    layoutManager = LinearLayoutManager(context, orientation, false)
}

fun RecyclerView.setUpHorizontalLayoutManager() {
    layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
}

fun RecyclerView.setUpVerticalLayoutManager() {
    layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
}

