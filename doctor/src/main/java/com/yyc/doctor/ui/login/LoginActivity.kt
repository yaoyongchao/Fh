package com.yyc.doctor.ui.login

import com.alibaba.android.arouter.facade.annotation.Route
import com.lottchina.baselib.base.BaseActivity
import com.vcaidian.customer.utils.RouteUrl
import com.yyc.doctor.R
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
    }

    override fun initData() {
    }

    override fun initListener() {
    }

}
