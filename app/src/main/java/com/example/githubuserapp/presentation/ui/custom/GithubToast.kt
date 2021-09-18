/**
 * Github Users Apps
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 15/09/21 01:00 PM
 * Last modified 15/09/21 01:00 PM by Nurholis*/
package com.example.githubuserapp.presentation.ui.custom

import android.app.Activity
import android.content.Context
import android.view.Gravity
import android.widget.Toast
import com.example.githubuserapp.R
/**
 * This class cutom the Positive Toast to display messages*/
class GithubToast(
    context: Context
): Toast(context) {
    /**
     * This function to handle the positive toast
     * @param activity to get object activity to calling layoutInflater to inflate layout.
     * @param message to handle messages that are passed into these parameters.
     * */
    fun showPositiveToast(activity: Activity, message: String) {
        val layout = activity.layoutInflater.inflate(
            R.layout.layout_custom_toast,
            activity.findViewById(R.id.toast_container)
        )
        //set the text of the textView to displaying messages
        val messageToast = layout.findViewById<GithubTextView>(R.id.toast_txt)
        messageToast.text = message

        //use the application extension function is common function
        this.apply {
            setGravity(Gravity.TOP, 0, 0)
            duration = LENGTH_SHORT
            @Suppress("DEPRECATION")
            view = layout
            show()
        }
    }

    /**
     * This function to handle the negative toast
     * @param activity to get object activity to calling layoutInflater to inflate layout.
     * @param message to handle messages that are passed into these parameters*/

    fun showToastDanger(activity: Activity, message: String) {
        val layout = activity.layoutInflater.inflate(
            R.layout.layout_custom_toast_danger,
            activity.findViewById(R.id.toast_container_danger)
        )
        //set the text of the TextView to displaying messages
        val messageToast = layout.findViewById<GithubTextView>(R.id.toast_txt_danger)
        messageToast.text = message

        //use the application extension function is common function
        this.apply {
            setGravity(Gravity.TOP, 0, 0)
            duration = LENGTH_LONG
            @Suppress("DEPRECATION")
            view = layout
            show()
        }
    }
}