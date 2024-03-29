package com.ygfh.doctor

import com.alibaba.android.arouter.launcher.ARouter
import com.fh.baselib.BaseApplication
import com.fh.baselib.utils.L

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

class DcApplication: BaseApplication() {
//    public var iwxapi: IWXAPI? = null
    override fun onCreate() {
        super.onCreate()
        L.initLogger(BuildConfig.DEBUG)
        L.d("启动Application")
    }

    companion object {
        // 单例模式： 双重校验锁式
        val instance: DcApplication by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            DcApplication()
        }
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