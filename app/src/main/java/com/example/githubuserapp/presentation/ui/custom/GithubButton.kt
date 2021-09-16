/**
 * Github Users Apps
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 15/09/21 11.10 PM
 * Last modified 15/09/21 11.10 PM by Nurholis*/
package com.example.githubuserapp.presentation.ui.custom

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import com.example.githubuserapp.R

class GithubButton: AppCompatButton {
    //add field in this class
    private var mContext: Context? = null
    //add constructor seconder
    /**
     * this constructor to handle constructor super class
     * because this class extending to
     * @see AppCompatButton
     */
    constructor(context: Context): super(context) {
        mContext = context
    }

    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {
        mContext = context
        init(context = context, attrs = attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super (context, attrs, defStyleAttr) {
        mContext = context
        init(context = context, attrs = attrs)
    }

    private fun init(context: Context, attrs: AttributeSet) {
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.GithubButton)
        var customFontIndex = typeArray.getInt(R.styleable.GithubButton_buttonViewFontFamily, 0)
        typeArray.recycle()
        //add array to adding font
        val fontViewFamily = listOf(
            "open_sans_regular.ttf",
            "open_sans_light.ttf",
            "open_sans_semi_bold.ttf",
            "open_sans_bold.ttf",
            "open_sans_bold_italic.ttf",
            "open_sans_extra_bold.ttf",
            "open_sans_extra_bold_italic.ttf",
            "open_sans_italic.ttf",
            "open_sans_light_italic.ttf",
        )
        //add conditions for the font used
        if (customFontIndex >= fontViewFamily.size) customFontIndex = fontViewFamily.size - 1
        val mFontPath = "font/${fontViewFamily[customFontIndex]}"
        val mTypeFace = Typeface.createFromAsset(context.assets, mFontPath)
        typeface = mTypeFace
    }

  interface SingleClickListener: OnClickListener {

        override fun onClick(p0: View?) {
            p0?.let {
                onClickCallback(it)
            }
        }
      fun onClickCallback(view: View)
    }
}