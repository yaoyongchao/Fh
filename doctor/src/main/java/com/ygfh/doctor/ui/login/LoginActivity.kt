package com.ygfh.doctor.ui.login

import com.alibaba.android.arouter.facade.annotation.Route
import com.fh.baselib.base.BaseActivity
import com.fh.baselib.utils.L
import com.fh.cplib.utils.JumpUtil
import com.fh.cplib.utils.RouteUrl
import com.tencent.mm.opensdk.modelmsg.SendAuth
import com.tencent.mm.opensdk.openapi.WXAPIFactory
import com.ygfh.doctor.BuildConfig
import com.ygfh.doctor.R
import gorden.rxbus2.Subscribe
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
            loginToWeiXin()

        }
    }

    override fun initData() {

    }

    override fun initListener() {
    }

    /**
     * 微信登录
     */
    private fun loginToWeiXin() {
        val mApi = WXAPIFactory.createWXAPI(this, BuildConfig.wxAppId, true)
        mApi.registerApp(BuildConfig.wxAppId)
        if (!mApi!!.isWXAppInstalled) {
            toast("您的设备未安装微信客户端")
        } else {
            val req = SendAuth.Req()
            req.scope = "snsapi_userinfo"
            req.state = "wechat_sdk_demo_test"
//            req.state = "wx_login"
            mApi.sendReq(req)
        }
    }

    @Subscribe(code = 1000)
    fun receive1000(code: Int) {
        when(code) {
            10 ->
                L.i("用户授权成功")
            20 ->
                L.i("用户取消")
            30 ->
                L.i("用户拒绝")
            40 ->
                L.i("其他")
        }
    }
}
