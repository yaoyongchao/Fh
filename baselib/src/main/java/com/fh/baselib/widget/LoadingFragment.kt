package com.yyc.netlib.widget

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fh.baselib.R

/**
 * Author: YongChao
 * Date: 19-8-14 上午11:45
 * Description: 网络加载框
 */

class LoadingFragment : DialogFragment() {


    companion object {
        // 单例模式： 双重校验锁式
        val instance: LoadingFragment by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            LoadingFragment()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //如果setCancelable()中参数为true，若点击dialog覆盖不到的activity的空白或者按返回键，
        //则进行cancel，状态检测依次onCancel()和onDismiss()。如参数为false，则按空白处或返回键无反应。缺省为true
        setCancelable(true);
        //  setStyle(style, theme);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog_MinWidth);
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_loading,null)
        return view
    }

    fun show(manager: FragmentManager) {
        show(manager,"LoadingFragment")
    }
}