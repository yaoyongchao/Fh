package com.lottchina.xdbao.ui.login

import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.lottchina.baselib.base.BaseActivity
import com.lottchina.baselib.utils.rx.RxTimer
import com.lottchina.xdbao.BuildConfig
import com.lottchina.xdbao.R
import com.vcaidian.customer.utils.JumpUtil
import com.vcaidian.customer.utils.RouteUrl
import kotlinx.android.synthetic.main.activity_startup_page.*

/**
 * @Author: Austin
 * @Date: 19-3-29
 * @Description: 启动页
 */
@Route(path = RouteUrl.startup)
class StartupPageActivity : BaseActivity() {
//    private val time : Long = 1000 * 1
    private val time : Long = 100 * 1
    override fun layoutId(): Int {
        return R.layout.activity_startup_page
    }

    override fun initView() {
        showToolbar(View.GONE)
        if (BuildConfig.isDebug)
            tv_version_name.text = getString(R.string.app_name) + BuildConfig.VERSION_NAME

        RxTimer().timer(time, RxTimer.RxAction {
            JumpUtil.jumpActivity(RouteUrl.binding)
            finish()
        })
    }

    override fun initData() {
    }

    override fun initListener() {
    }

    override fun isFullScreen(): Boolean {
        return true
    }
}
