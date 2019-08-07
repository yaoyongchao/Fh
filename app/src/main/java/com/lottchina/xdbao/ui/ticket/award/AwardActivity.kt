package com.lottchina.xdbao.ui.ticket.award

import android.os.Bundle
import android.util.Log
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.lottchina.baselib.base.BaseActivity
import com.lottchina.xdbao.net.CpSocketUtil
import com.lottchina.xdbao.protocol.message.StationMessage
import com.lottchina.xdbao.utils.CommonUtil
import com.vcaidian.customer.utils.RouteUrl
import gorden.rxbus2.RxBus
import gorden.rxbus2.Subscribe
import gorden.rxbus2.ThreadMode
import kotlinx.android.synthetic.main.activity_award.*
import okhttp3.*
import okhttp3.internal.ws.RealWebSocket
import okio.ByteString
import java.util.*
import java.util.concurrent.TimeUnit


/**
 * @Author: Austin
 * @Date: 19-5-9
 * @Description: 派奖
 */
@Route(path = RouteUrl.award)
class AwardActivity : BaseActivity(), CpSocketUtil.MessageListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RxBus.get().register(this)
    }
    override fun layoutId(): Int {
        return com.lottchina.xdbao.R.layout.activity_award
    }

    override fun initView() {
        setToolbarTitle(getString(com.lottchina.xdbao.R.string.award))
        var aa: RealWebSocket

        Thread(Runnable {
            CpSocketUtil.getInstance().startSocketComm(CommonUtil.getUserId(),CommonUtil.getToken(),this)
        }).start()

    }

    override fun initData() {
    }

    override fun initListener() {
        btn.setOnClickListener(this)
        btn2.setOnClickListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()

    }

    override fun onClick(v: View?) {
        super.onClick(v)
        when(v?.id) {
            com.lottchina.xdbao.R.id.btn -> {
                start()
            }
            com.lottchina.xdbao.R.id.btn2 -> {
//                CpSocketUtil.instance.startSocketLink(1,"2")
            }
        }

    }

    @Subscribe(code = 1005,threadMode = ThreadMode.MAIN)
    fun receive1005() {
        tv1.setText("code105")
    }

    @Subscribe(code = 1005,threadMode = ThreadMode.MAIN)
    fun receive1005(msg: String) {
        tv2.setText("code105" + msg)
    }


    lateinit var mSocket:WebSocket

    private fun start() {
        Log.e("aa","start")
        val mOkHttpClient = OkHttpClient.Builder()
                .readTimeout(3, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(3, TimeUnit.SECONDS)//设置写的超时时间
                .connectTimeout(3, TimeUnit.SECONDS)//设置连接超时时间
                .build()

        val url = "http://121.42.197.2:8888"

        val request = Request.Builder().url(url).build()
        val socketListener = EchoWebSocketListener()
        mOkHttpClient.newWebSocket(request, socketListener)
        mOkHttpClient.dispatcher().executorService().shutdown()
    }

    private inner class EchoWebSocketListener : WebSocketListener() {

        override fun onOpen(webSocket: WebSocket?, response: Response?) {
            super.onOpen(webSocket, response)
            mSocket = webSocket!!
            val openid = "1"
            //连接成功后，发送登录信息
            val message = "{\"type\":\"login\",\"user_id\":\"$openid\"}"
            mSocket.send(message)
            mSocket.send("welcome")
            mSocket.send(ByteString.decodeHex("adef"))
            mSocket.close(1000, "再见")
            output("连接成功！")
        }

        override fun onMessage(webSocket: WebSocket?, bytes: ByteString?) {
            super.onMessage(webSocket, bytes)
            output("receive bytes:" + bytes!!.hex())
        }

        override fun onMessage(webSocket: WebSocket?, text: String?) {
            super.onMessage(webSocket, text)
            output("receive text:" + text!!)
            //收到服务器端发送来的信息后，每隔25秒发送一次心跳包
            val message = "{\"type\":\"heartbeat\",\"user_id\":\"heartbeat\"}"
            val timer = Timer()
            timer.schedule(object : TimerTask() {
                override fun run() {
                    mSocket.send(message)
                }
            }, 25000)
        }

        override fun onClosed(webSocket: WebSocket?, code: Int, reason: String?) {
            super.onClosed(webSocket, code, reason)
            output("closed:" + reason!!)
        }

        override fun onClosing(webSocket: WebSocket?, code: Int, reason: String?) {
            super.onClosing(webSocket, code, reason)
            output("closing:" + reason!!)
        }

        override fun onFailure(webSocket: WebSocket?, t: Throwable?, response: Response?) {
            super.onFailure(webSocket, t, response)
            output("failure:" + t!!.message)
        }
    }

    private fun output(text: String) {
        runOnUiThread(Runnable {
            tv1.setText( "\n\n" + text)
        })
    }

    override fun onMessage(msgList: MutableList<StationMessage>?) {

    }

}
