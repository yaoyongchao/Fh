package com.ygfh.doctor.ui.login

import com.alibaba.android.arouter.facade.annotation.Route
import com.fh.baselib.base.BaseActivity
import com.fh.baselib.utils.rx.RxTimer
import com.fh.cplib.utils.JumpUtil
import com.fh.cplib.utils.RouteUrl
import com.ygfh.doctor.R
import com.ygfh.doctor.utils.CommonUtil

/**
 * Author: YongChao
 * Date: 19-8-7 下午4:52
 * Description:  启动页
 */
@Route(path = RouteUrl.startup)
class StartupPageActivity : BaseActivity() {
    private val time : Long = 1000 * 1
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

    }

    override fun isFullScreen(): Boolean {
        return true
    }

    override fun initData() {
    }

    override fun initListener() {
    }

}
