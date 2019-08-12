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
        //        IWXAPI mApi = BaseApplication.getInstance().getApi();
        val mApi = WXAPIFactory.createWXAPI(this, BuildConfig.wxAppId, true)
        mApi.registerApp(BuildConfig.wxAppId)
        L.d("appid:" + BuildConfig.wxAppId)
//        CommonMethod.setWxApi(mApi)

        if (!mApi!!.isWXAppInstalled) {
            toast("您的设备未安装微信客户端")
        } else {
            val req = SendAuth.Req()
            req.scope = "snsapi_userinfo"
            req.state = "wechat_sdk_demo_test"
//            req.state = "wx_login"
            mApi.sendReq(req)
        }

//        if (mApi != null && mApi.isWXAppInstalled) {
//            val req = SendAuth.Req()
//            req.scope = "snsapi_userinfo"
////            req.state = "wechat_sdk_demo_test"
//            req.state = "wx_login"
//            mApi.sendReq(req)
//        } else
//            Toast.makeText(this, "用户未安装微信", Toast.LENGTH_SHORT).show()
    }

}
