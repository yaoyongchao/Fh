package com.ygfh.doctor.ui.login

import com.alibaba.android.arouter.facade.annotation.Route
import com.fh.baselib.base.BaseActivity
import com.fh.baselib.utils.L
import com.fh.cplib.utils.JumpUtil
import com.fh.cplib.utils.RouteUrl
import com.ygfh.doctor.R
import kotlinx.android.synthetic.main.activity_login.*

/**
 * Author: YongChao
 * Date: 19-8-7 下午4:22
 * Description: 登录页面
 */

@Route(path = RouteUrl.login)
class LoginActivity : BaseActivity() {
    override fun layoutId(): Int {
        return R.layout.activity_login
    }

    override fun initView() {
        // 申请动态权限
        btn.setOnClickListener {
            JumpUtil.jumpActivity(RouteUrl.home)

        }

        btn_wx.setOnClickListener {
            L.d("微信登录")

        }
    }

    override fun initData() {

    }

    override fun initListener() {
    }


}
