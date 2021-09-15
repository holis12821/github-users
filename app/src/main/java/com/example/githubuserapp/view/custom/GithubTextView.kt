package com.example.githubuserapp.view.custom

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.example.githubuserapp.R

class GithubTextView: AppCompatTextView {
    //add field in class
    private var mContext: Context? = null
    private var mCustomFont: String? = null

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
        val customFont = typeArray.getInteger(R.styleable.GithubTextView_font_view_type, 0)
        var fontViewType = 0
        //add conditions for the font used
        when(customFont) {
            1 -> fontViewType = R.font.open_sans_regular
            2 -> fontViewType = R.font.open_sans_bold
            3 -> fontViewType = R.font.open_sans_bold_italic
            4 -> fontViewType = R.font.open_sans_extra_bold
            5 -> fontViewType = R.font.open_sans_extra_bold_italic
            6 -> fontViewType = R.font.open_sans_italic
            7 -> fontViewType = R.font.open_sans_light
            8 -> fontViewType = R.font.open_sans_light_italic
            9 -> fontViewType = R.font.open_sans_semi_bold
            10 -> fontViewType = R.font.open_sans_semi_bold_italic
        }

        mCustomFont = resources.getString(fontViewType)
        //add Typeface to create font from assets
        val typeFace = Typeface.createFromAsset(context.assets,
            "font/ $customFont.ttf"
        )
        typeface = typeFace
        typeArray.recycle()
    }
}