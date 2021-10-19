/**
 * Github Users Apps
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 15/09/21 01:00 PM
 * Last modified 15/09/21 01:00 PM by Nurholis*/
package com.example.githubuserapp.presentation.ui.activity.splashscreen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.example.githubuserapp.R
import com.example.githubuserapp.core.BaseActivity
import com.example.githubuserapp.databinding.ActivitySplashScreenBinding
import com.example.githubuserapp.external.utils.LogUtils
import com.example.githubuserapp.presentation.ui.activity.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashScreenActivity: BaseActivity<ActivitySplashScreenBinding>() {

    private val viewModel by viewModel<SplashScreenViewModel>()

    override fun getResLayoutId(): Int = R.layout.activity_splash_screen

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        showSplashScreen()
        onObserverTheme()
    }

    private fun showSplashScreen() {
        object : Thread() {
            override fun run() {
                try {
                    sleep(3_000)
                    val intent = Intent(this@SplashScreenActivity,
                    MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
                } catch (e: InterruptedException) {
                   LogUtils.print(e)
                }
            }
        }.start()
    }

    //set Theme DarkMode and LightMode
    private fun onObserverTheme() {
        viewModel.getThemeSettings().observe(this, { isDarkModeActive ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                binding.iconGithub.setImageResource(R.drawable.ic_github_icon_white)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                binding.iconGithub.setImageResource(R.drawable.ic_github_icon_black)
            }
        })
    }
}