package com.vcaidian.baselib.utils

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import com.lottchina.baselib.BaseApplication

/**
 * Author: Austin
 * Date: 2018/10/9
 * Description:
 */
class ToastUtil {
    companion object {
        @SuppressLint("StaticFieldLeak")
        fun show(mContext: Context,msg: String) {
            Toast.makeText(mContext,msg,Toast.LENGTH_SHORT).show()
        }

        fun showLong(mContext: Context,msg: String) {
            Toast.makeText(mContext,msg,Toast.LENGTH_LONG).show()
        }

        fun show(msg:String) {
            show(BaseApplication.instance.applicationContext,msg)
        }

        fun showLong(msg: String) {
            showLong(BaseApplication.instance.applicationContext,msg)
        }
    }
}