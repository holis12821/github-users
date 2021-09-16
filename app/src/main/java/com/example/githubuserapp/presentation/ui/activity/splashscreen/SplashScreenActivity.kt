/**
 * Github Users Apps
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 15/09/21 13.00 PM
 * Last modified 15/09/21 13.00 PM by Nurholis*/
package com.example.githubuserapp.presentation.ui.activity.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.githubuserapp.R
import com.example.githubuserapp.databinding.ActivitySplashScreenBinding
import com.example.githubuserapp.presentation.ui.activity.MainActivity

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen)
        showSplashScreen()
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
                    Log.d("Interrupted Exception", e.message ?: "")
                }
            }
        }.start()
    }
}