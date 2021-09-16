/**
 * Github Users Apps
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 15/09/21 13.00 PM
 * Last modified 15/09/21 13.00 PM by Nurholis*/
package com.example.githubuserapp.presentation.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuserapp.R
import com.example.githubuserapp.databinding.ActivityMainBinding
import com.example.githubuserapp.data.response.Users
import com.example.githubuserapp.presentation.ui.activity.detailuser.DetailUserActivity
import com.example.githubuserapp.presentation.ui.adapter.AdapterClickListener
import com.example.githubuserapp.presentation.ui.adapter.UsersAdapter
import com.example.githubuserapp.presentation.ui.custom.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UsersAdapter
    private lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initView()
        setUpNavigationView()
    }

    private fun initView() {
            adapter = UsersAdapter(arrayListOf()).apply {
            listener = object : AdapterClickListener<Users?> {
                override fun onItemClickCallback(data: Users?) {
                   val intent = Intent(this@MainActivity, DetailUserActivity::class.java)
                    intent.putExtra(DetailUserActivity.KEY_EXTRA_USERS, data)
                    startActivity(intent)
                }

                override fun onViewClickCallback(view: View, data: Users?) {

                }

            }
        }

        //set LayoutManager that this recyclerView will use
        binding.rvListUsers.setHasFixedSize(true)
        binding.rvListUsers.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        //setdata
        binding.rvListUsers.adapter = adapter
    }

    private fun setUpNavigationView(){
        navigationView = NavigationView(this)
            .setupTitle("Github Users")
    }
}