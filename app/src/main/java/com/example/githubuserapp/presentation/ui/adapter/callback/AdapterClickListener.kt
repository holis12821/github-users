/**
 * Github Users Apps
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 15/09/21 01:00 PM
 * Last modified 15/09/21 01:00 PM by Nurholis*/
package com.example.githubuserapp.presentation.ui.adapter.callback

import android.view.View

interface AdapterClickListener<T> {
    fun onItemClickCallback(data: T)
    fun onViewClickCallback(view: View, data: T)
}