package com.yyc.doctor

import com.alibaba.android.arouter.launcher.ARouter
import com.lottchina.baselib.BaseApplication
import com.lottchina.baselib.utils.L

/**
 * Author: Austin
 * Date: 19-3-27
 * Description:
 */
/**
 * Author: YongChao
 * Date: 19-8-7 上午10:19
 * Description:
 */

/**
 * Author: YongChao
 * Date: $date$ $time$
 * Description: $description$
 */

class DcApplication : BaseApplication(){

    override fun onCreate() {
        super.onCreate()
        L.initLogger(BuildConfig.DEBUG)
        L.d("启动Application")
    }

    override fun initViews() {
        super.initViews()
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
    }
}