/**
 * Github Users Apps
 * Copyright (c) 2021 All rights reserved.
 * Created by Nurholis on 15/09/21 13.00 PM
 * Last modified 15/09/21 13.00 PM by Nurholis*/
package com.example.githubuserapp.presentation.ui.custom

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.example.githubuserapp.R

class GithubTextView: AppCompatTextView {
    //add field in class
    private var mContext: Context? = null

    //add constructor seconder
    //and overloading constructor to this class
    constructor(context: Context): super(context) {
        mContext = context
    }

    constructor(context: Context, attrs: AttributeSet): super(context,  attrs) {
        mContext = context
        setCustomFont(context = context, attrs = attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int): super(context, attrs, defStyle) {
        mContext = context
        setCustomFont(context = context, attrs = attrs)
    }

    private fun setCustomFont(context: Context, attrs: AttributeSet) {
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.GithubTextView)
        var customFontIndex = typeArray.getInt(R.styleable.GithubTextView_textViewFontFamily, 0)
        typeArray.recycle()
        //add array to adding font
        val fontViewFamily =  listOf(
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
}