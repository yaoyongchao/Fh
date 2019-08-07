package com.lottchina.baselib.widget

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import com.lottchina.baselib.R


class MyDivider constructor(context: Context, orientation: Int) : DividerItemDecoration(context,orientation){

    init {
        val drawable = ContextCompat.getDrawable(context, R.drawable.divider_shape)
        setDrawable(drawable!!)
    }

}