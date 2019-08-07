package com.yyc.doctor.ui.login

import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.lottchina.baselib.base.BaseActivity
import com.lottchina.baselib.utils.rx.RxTimer
import com.vcaidian.customer.utils.JumpUtil
import com.vcaidian.customer.utils.RouteUrl
import com.yyc.doctor.R
import com.yyc.doctor.utils.CommonUtil
import kotlinx.android.synthetic.main.activity_startup_page.*

/**
 * Author: YongChao
 * Date: 19-8-7 下午4:52
 * Description:  启动页
 */
@Route(path = RouteUrl.startup)
class StartupPageActivity : BaseActivity() {
    private val time : Long = 100 * 1
    override fun layoutId(): Int {
        return R.layout.activity_startup_page
    }

    override fun initView() {
        showToolbar(false)
        if (CommonUtil.isFirstUse()) {
            JumpUtil.jumpActivity(RouteUrl.guide)
        } else {
            RxTimer().timer(time, RxTimer.RxAction {
                JumpUtil.jumpActivity(RouteUrl.login)
                finish()
            })
        }

        btn.setOnClickListener {
            ARouter.getInstance().build("/login/ab").navigation()
        }

    }

    override fun isFullScreen(): Boolean {
        return true
    }

    override fun initData() {
    }

    override fun initListener() {
    }

}
