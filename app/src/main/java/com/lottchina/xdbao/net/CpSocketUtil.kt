/*
package com.lottchina.xdbao.net

import android.content.Context
import android.util.Log
import com.lottchina.baselib.BaseApplication
import com.lottchina.xdbao.BuildConfig
import com.lottchina.xdbao.net.websocketUtils.WsManager
import com.lottchina.xdbao.net.websocketUtils.listener.WsStatusListener
import okhttp3.OkHttpClient
import okhttp3.Response
import okio.ByteString
import java.util.concurrent.TimeUnit

*/
/**
 * Author: Austin
 * Date: 19-5-9
 * Description:
 *//*

class CpSocketUtil private constructor():WsStatusListener() {
    val TAG = "CpSocketUtil"
    private lateinit var context: Context
    private lateinit var wsManager: WsManager
//    private lateinit var wsStatusListener: WsStatusListener
    companion object {
        val instance: CpSocketUtil by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            CpSocketUtil() }
    }

    fun init(ctx: Context) {
        this.context = ctx;
    }

    fun isWsManagerInitialzed()=::wsManager.isInitialized
    */
/**
     * 启动Socket链接
     *//*

    fun startSocketLink(userId:Int,token: String) {
        log("启动链接")
        if (isWsManagerInitialzed() && wsManager.isWsConnected) {
            return
        }

        var strUrl = "ws://121.42.197:8888/"

        wsManager = WsManager.Builder(BaseApplication.appContext).client(
                OkHttpClient().newBuilder()
                        .pingInterval(5, TimeUnit.SECONDS)
                        .retryOnConnectionFailure(true)
                        .build())
                .needReconnect(true)
//                .wsUrl(CpApi.CP_SOCKET_URL)
                .wsUrl(strUrl)
                .build()
        wsManager.setWsStatusListener(this)
        wsManager.startConnect()

    }

    override fun onOpen(response: Response?) {
        super.onOpen(response)
        log("服务器链接成功")
    }

    override fun onMessage(text: String?) {
        super.onMessage(text)
        log("收到服务器消息：text")
    }

    override fun onMessage(bytes: ByteString?) {
        super.onMessage(bytes)
        log("收到服务器消息：bytes")
    }

    override fun onReconnect() {
        super.onReconnect()
        log("服务器重连中")
    }

    override fun onClosing(code: Int, reason: String?) {
        super.onClosing(code, reason)
        log("服务器链接关闭中")
    }

    override fun onClosed(code: Int, reason: String?) {
        super.onClosed(code, reason)
        log("服务器链接已关闭")
    }

    override fun onFailure(t: Throwable?, response: Response?) {
        super.onFailure(t, response)
        log("服务器链接失败")
    }

    fun log(msg:String) {
        if (BuildConfig.isDebug)
            Log.i(TAG,msg)
    }
}*/
