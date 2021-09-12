package com.example.githubuserapp.view.adapter

import android.view.View

interface AdapterClickListener<T> {
    fun onItemClickCallback(data: T)
    fun onViewClickCallback(view: View, data: T)
}