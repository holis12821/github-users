/**
 * Github Users Apps
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 16/09/21 08:53 PM
 * Last modified 16/09/21 08:53 PM by Nurholis*/
package com.example.githubuserapp.core

import android.app.Activity
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.githubuserapp.presentation.ui.custom.GithubProgressDialog
import com.example.githubuserapp.presentation.ui.custom.GithubToast

/**
 * This BaseActivity class is used for activity that using view
 * and data binding by extending this class will have common function
 * for binding the view.
 * for extend this class we need two parameter
 * @param B for view data binding and
 * @see ViewDataBinding is both parameter required and can't
 * set to null. This parameter is generic constraint type parameter which
 * is used to restrict what generic types to attach.
 * This generic type is the same of Bounded parameter Type in Java
 * To see documentation on my projects please visit
 * @see "https://github.com/holis12821"
 * */

abstract class BaseActivity<B : ViewDataBinding>: AppCompatActivity() {

    /**
     * This variable is used for showing Toast Positive and Error
     * to displaying messages
     * */
    private lateinit var githubToast: GithubToast

    /**
     * This variable is use for binding the view*/
    protected lateinit var binding: B

    /**
     * This function is used for set the view layout
     * */
    @LayoutRes
    protected abstract fun getResLayoutId(): Int
    //define progress dialog
    private lateinit var progressDialog: GithubProgressDialog

    /**
     *This function used for set the action
     * when the activity was created*/
    protected abstract fun onActivityCreated(savedInstanceState: Bundle?)

    /**
     * This function is used for init all function some think like that
     * when activity is read to layout and next to call function
     * @onActivityCreated.
     * There's have function for binding the view with data binding.
     * @onCreate this function will be executed when the layout
     * of the activity has been attach to the activity.
     * @lifeCycleOwner is an interface that shows that this class
     * has a lifecycle such as an activity or a fragment.
     * so the activity or fragment implements a
     * @LifeCycleOwner, then the activity and fragment have properties that
     * are owned by the interface, so they have a lifecycle*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding view is here
        binding = DataBindingUtil.setContentView<B>(this, getResLayoutId())
            .apply {
                lifecycleOwner = this@BaseActivity
            }
        githubToast = GithubToast(this)
        progressDialog = GithubProgressDialog(this)
        onActivityCreated(savedInstanceState = savedInstanceState)
    }

    /**
     * This function displaying Toast Positive Messages
     * */
    fun showPositiveToast(activity: Activity, message: () -> String) {
        if (message.invoke().isEmpty()) return
        else githubToast.showPositiveToast(activity = activity, message = message.invoke())
    }

    /**
     * This function displaying Toast Error Messages
     * */
    fun showToastDanger(activity: Activity, message: () -> String) {
        if (message.invoke().isEmpty()) return
        else githubToast.showToastDanger(activity = activity, message = message.invoke())
    }

    /**
     * This function displaying progress loading dialog
     * */
    fun showDialogProgress(): GithubProgressDialog {
        try {
            progressDialog.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return progressDialog
    }

    /**
     * This function hiding progress loading dialog
     * */
    fun hideProgress() {
        try {
            progressDialog.dismiss()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        try {
            progressDialog.dismiss()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}