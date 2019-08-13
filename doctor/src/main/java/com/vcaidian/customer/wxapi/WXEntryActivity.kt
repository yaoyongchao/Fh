package com.vcaidian.customer.wxapi

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.fh.baselib.utils.L
import com.fh.baselib.utils.ToastUtil
import com.tencent.mm.opensdk.modelbase.BaseReq
import com.tencent.mm.opensdk.modelbase.BaseResp
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler
import com.tencent.mm.opensdk.openapi.WXAPIFactory
import com.ygfh.doctor.BuildConfig
import gorden.rxbus2.RxBus
import okhttp3.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException




class WXEntryActivity : AppCompatActivity(), IWXAPIEventHandler {
    /**
     * 微信登录相关
     */
    private var api: IWXAPI? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 隐藏状态栏
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN)

//        setContentView(R.layout.activity_wxentry)
        //通过WXAPIFactory工厂获取IWXApI的示例
        api = WXAPIFactory.createWXAPI(this,BuildConfig.wxAppId,false)
        L.e("回调页面--appId: ${BuildConfig.wxAppId}")
        //将应用的appid注册到微信
        api!!.registerApp(BuildConfig.wxAppId)
        api!!.handleIntent(intent, this)
        //注意：
        //第三方开发者如果使用透明界面来实现WXEntryActivity，需要判断handleIntent的返回值，如果返回值为false，则说明入参不合法未被SDK处理，应finish当前透明界面，避免外部通过传递非法参数的Intent导致停留在透明界面，引起用户的疑惑
        /*try {
            val result = api!!.handleIntent(intent, this)
            if (!result) {
                L.e("参数不合法，未被SDK处理，退出")
                finish()
            }
        } catch (e: Exception) {
            L.e("微信登录失败！！")
            e.printStackTrace()
        }*/
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

    /**
     * RxBus code 10000    int 10 授权成功   int 20 用户取消   int 30 用户拒绝授权 int 40 其他
     */
    override fun onResp(resp: BaseResp?) {
        when (resp!!.errCode) {
            BaseResp.ErrCode.ERR_OK -> {
                L.e("用户同意")
                RxBus.get().send(1000,10)
//                val code = (resp as SendAuth.Resp).code
//                getAccessToken(code)
            }
            BaseResp.ErrCode.ERR_USER_CANCEL -> {
                RxBus.get().send(1000,20)
                L.e("用户取消")
            }
            BaseResp.ErrCode.ERR_AUTH_DENIED -> {
                RxBus.get().send(1000,30)
                L.e("用户拒绝授权")
            }
            else -> {
                RxBus.get().send(1000,40)
            }
        }
        finish()
    }
    override fun onReq(req: BaseReq?) {
    }

    private fun getAccessToken(code: String) {
        //获取授权
        val loginUrl = StringBuffer()
        loginUrl.append("https://api.weixin.qq.com/sns/oauth2/access_token")
                .append("?appid=")
                .append("wx45ccf8958a0a24c7")//需要更换
                .append("&secret=")
                .append("e9c071f3326663856bc6cf02c2d6b657")//需要更换，切记
                .append("&code=")
                .append(code)
                .append("&grant_type=authorization_code")
        L.d(loginUrl.toString())

        val okHttpClient = OkHttpClient()
        val request = Request.Builder()
                .url(loginUrl.toString())
                .get()//默认就是GET请求，可以不写
                .build()
        val call = okHttpClient.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                ToastUtil.show("微信登录失败。")
            }
            override fun onResponse(call: Call, response: Response) {
                val responseInfo = response.body()!!.string()
                L.d("onResponse: $responseInfo")
                var access: String? = null
                var openId: String? = null
                try {
                    val jsonObject = JSONObject(responseInfo)
                    access = jsonObject.getString("access_token")
                    openId = jsonObject.getString("openid")
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
                L.e("access---:$access  ---------- openId------: $openId")
            }
        })
    }
}
