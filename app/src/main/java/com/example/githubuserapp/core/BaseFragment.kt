/**
 * Github Users Apps
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 26/09/21 01:02 PM
 * Last modified 26/09/21 01:03 PM by Nurholis*/
package com.example.githubuserapp.core

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.githubuserapp.presentation.ui.custom.GithubProgressDialog
import com.example.githubuserapp.presentation.ui.custom.GithubToast

/**
* This base fragment class is used for fragment that using view
* and data binding. By extending this class will have common
* function for binding the view.
* for extended this class we need two parameters :
* @see ViewDataBinding
* @param B for view data binding.
* */
abstract class BaseFragment<B : ViewDataBinding>: Fragment() {
    /**
     * This variable is used for showing Toast Positive and Error
     * to displaying messages*/
    private lateinit var githubToast: GithubToast

    /**
    * This variable used for binding the view*/
    protected  var binding: B? = null

    /**
     * This function is used for set the view layout
     * and add annotation @LayoutRes*/
    @LayoutRes
    protected abstract fun getResLayoutId(): Int

    //define progress dialog
    private lateinit var progressDialog: GithubProgressDialog

    /**
     * @see onViewCreated this function is used for set
     * the action when the view was created*/
    protected abstract fun onViewCreated()

    /**
     * This function is used for init all function when fragment is created.
     * There're have function for binding the view with data binding.
     * @see onCreateView is a function when layout on fragment is attach
     * or if not attachment in the layout then it won't run the
     * @see onViewCreated function.
     * @see getViewLifecycleOwner called when in a fragment, which fragment
     * will follow the lifecycle of the activity.
     *  @LifeCycleOwner is an interface that shows that this class
     * has a lifecycle such as an activity or a fragment.
     * so the activity or fragment implements a
     * @LifeCycleOwner, then the activity and fragment have properties that
     * are owned by the interface, so they have a lifecycle.*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<B>(inflater,
        getResLayoutId(), container, false)
            .apply {
                lifecycleOwner = this@BaseFragment
            }
        activity?.let {
            githubToast = GithubToast(it)
        }
        activity?.let {
            progressDialog = GithubProgressDialog(it)
        }
        onViewCreated()
        return binding?.root
    }

    /**
     * This function displaying Toast Positive Messages */
    fun showPositiveToast(activity: Activity, message: () -> String) {
        if (message.invoke().isEmpty()) return
        else githubToast.showPositiveToast(activity, message.invoke())
    }

    /**
     * This function displaying Toast Error Messages
     * */
    fun showToastDanger(activity: Activity, message: () -> String) {
        if (message.invoke().isEmpty()) return
        else githubToast.showToastDanger(activity, message.invoke())
    }

    fun showDialogProgress(): GithubProgressDialog {
        try {
            progressDialog.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return progressDialog
    }

    /**
     * This function hiding progress loading dialog*/
    fun hideProgress() {
        try {
            progressDialog.dismiss()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}