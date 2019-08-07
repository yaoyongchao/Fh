package com.lottchina.xdbao

import com.alibaba.android.arouter.launcher.ARouter
import com.lottchina.baselib.BaseApplication
import com.lottchina.baselib.utils.L
import com.lottchina.xdbao.net.CpSocketUtil

/**
 * Author: Austin
 * Date: 19-3-27
 * Description: 需要继承基础依赖包的 BaseApplication.
 */
class CpApplication: BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        L.initLogger(BuildConfig.isDebug)
        L.d("启动Application")
//        CpSocketUtil.instance.init(this)
        CpSocketUtil.getInstance().init(this)
    }

    override fun initViews() {
        super.initViews()
        //初始化ARouter
        if (BuildConfig.isDebug) {
            ARouter.openLog()//打开日志
            ARouter.openDebug()//打开调式模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this)
    }
}