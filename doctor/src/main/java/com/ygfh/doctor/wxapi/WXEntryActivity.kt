package com.ygfh.doctor.wxapi

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import com.fh.baselib.utils.L
import com.fh.baselib.utils.ToastUtil
import com.tencent.mm.opensdk.modelbase.BaseReq
import com.tencent.mm.opensdk.modelbase.BaseResp
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler
import com.tencent.mm.opensdk.openapi.WXAPIFactory
import com.ygfh.doctor.BuildConfig




class WXEntryActivity :  AppCompatActivity(), IWXAPIEventHandler {
    /**
     * 微信登录相关
     */
    private var api: IWXAPI? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)

//        setContentView(R.layout.activity_wxentry)
        //通过WXAPIFactory工厂获取IWXApI的示例
        api = WXAPIFactory.createWXAPI(this,BuildConfig.wxAppId,false)
        //将应用的appid注册到微信
//        api!!.registerApp(BuildConfig.wxAppId)
        //注意：
        //第三方开发者如果使用透明界面来实现WXEntryActivity，需要判断handleIntent的返回值，如果返回值为false，则说明入参不合法未被SDK处理，应finish当前透明界面，避免外部通过传递非法参数的Intent导致停留在透明界面，引起用户的疑惑
        try {
            val result = api!!.handleIntent(intent, this)
            if (!result) {
                L.e("参数不合法，未被SDK处理，退出")
                finish()
            }
        } catch (e: Exception) {
            L.e("微信登录失败！！")
            e.printStackTrace()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        api!!.handleIntent(data,this)

    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        api!!.handleIntent(intent,this)
        finish()

    }

    override fun onResp(resp: BaseResp?) {
        L.e("登录回调")
        var result = ""
        when (resp!!.errCode) {
            BaseResp.ErrCode.ERR_OK -> {
                result = "发送成功"
                L.e("用户同意")

                finish()
            }
            BaseResp.ErrCode.ERR_USER_CANCEL -> {
                result = "发送取消"
                L.e("用户取消")
                finish()
            }
            BaseResp.ErrCode.ERR_AUTH_DENIED -> {
                result = "发送被拒绝"
                L.e("用户拒绝授权")
                finish()
            }
            else -> {
                result = "发送返回"
                finish()
            }
        }
        ToastUtil.show(result)
    }

    override fun onReq(req: BaseReq?) {


    }
}
