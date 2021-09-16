/**
 * Github Users Apps
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 15/09/21 13.00 PM
 * Last modified 15/09/21 13.00 PM by Nurholis*/
package com.example.githubuserapp.presentation.ui.custom

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import com.example.githubuserapp.R

class GithubProgressDialog(
    context: Context
): Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.github_progress_dialog)
        window?.setBackgroundDrawableResource(android.R.color.transparent)
    }
}