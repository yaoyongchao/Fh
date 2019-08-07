package com.lottchina.baselib

import android.app.Application
import android.content.Context
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.constant.SpinnerStyle
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader

/**
 * Author: Austin
 * Date: 19-3-27
 * Description: Application基础类
 */

open class BaseApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initViews()
    }

    companion object {
        // 单例模式： 双重校验锁式
        val instance: Application by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            BaseApplication()
        }

        lateinit var appContext: Context

        //配置： SmartRefreshLayout
        init {//static 代码段可以防止内存泄露
            //设置全局的Header构建器
            SmartRefreshLayout.setDefaultRefreshHeaderCreater { context, layout ->
                layout.setPrimaryColorsId(R.color.bgDefault, R.color.colorTxtDefault)//全局设置主题颜色
                ClassicsHeader(context).setSpinnerStyle(SpinnerStyle.Translate)//指定为经典Header，默认是 贝塞尔雷达Header
            }
            //设置全局的Footer构建器
            SmartRefreshLayout.setDefaultRefreshFooterCreater { context, layout ->
                //指定为经典Footer，默认是 BallPulseFooter
                ClassicsFooter(context).setSpinnerStyle(SpinnerStyle.Translate)
            }
        }
    }

    open fun initViews() {
        appContext = applicationContext
    }
}