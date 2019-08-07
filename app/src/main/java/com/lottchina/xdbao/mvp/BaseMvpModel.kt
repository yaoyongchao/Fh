package com.lottchina.xdbao.mvp

import com.lottchina.baselib.utils.GsonUtil
import com.lottchina.baselib.utils.L
import com.lottchina.baselib.utils.rx.MyRxScheduler
import com.lottchina.cplib.common.Command
import com.lottchina.cplib.data.base.BaseRequestBody
import com.lottchina.cplib.data.bean.body.login.LoginResBody
import com.lottchina.xdbao.net.CpServiceFactory
import com.vcaidian.baselib.mvp.BaseModel
import com.vcaidian.customer.bean.base.BaseRequestBean
import com.vcaidian.customer.bean.base.RequestHead
import com.vcaidian.customer.common.CommonParam
import com.vcaidian.customer.common.ResponseCode
import com.vcaidian.customer.utils.MD5Util
import com.vcaidian.customer.utils.TimestampUtil
import com.vcaidian.wclib.mvp.MVPListener
import com.vcaidian.wclib.wc.ErrBean
import org.json.JSONObject

/**
 * Author: Austin
 * Date: 19-5-6
 * Description:
 */
abstract class BaseMvpModel: BaseModel {

    fun loadData(userId:Int, body: BaseRequestBody, listener: MVPListener<LoginResBody>) {

        val pwd = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
        val strTimeStamp = TimestampUtil.timeStamp
//        var gsonBody = Gson().toJson(body)
        var gsonBody = GsonUtil.GsonString(body)
        L.e("pwd: $pwd")
        L.e("body: $gsonBody")
        var digest = MD5Util.md5((pwd + gsonBody + strTimeStamp).toByteArray(charset("UTF-8")))

        var head = RequestHead()
        head.cmd = Command.CMD_TERMINAL_LOGIN
        head.digest = digest
        head.digestType = CommonParam.DIGEST_TYPE
        head.userId = if (userId == 0){""}else{userId.toString()}
        head.timeStamp = strTimeStamp
        head.userType = CommonParam.USER_TYPE

        var requestBean = BaseRequestBean(head, gsonBody)

//        var gsonHead = Gson().toJson(head)
        var gsonHead = GsonUtil.GsonString(head)

        L.e("head: $gsonHead \nbody: $gsonBody")

        CpServiceFactory.getService()
                .loadData(gsonHead,gsonBody)
                .compose(MyRxScheduler.ioMain())
                /*.map {
                    var jsonObject: JSONObject = JSONObject(it.body)
                    var body = Gson().fromJson(it.body, BaseResponseBody::class.java)
                    body
                }*/.subscribe(
                {
                    var jsonObject  = JSONObject(it.body)
//                    var errBean: ErrBean = Gson().fromJson(jsonObject.getJSONObject("err").toString(),ErrBean::class.java)
                    var errBean: ErrBean = GsonUtil.GsonToBean(jsonObject.getJSONObject("err").toString(), ErrBean::class.java)
                    if (ResponseCode.SUCCESS == errBean.code) {//请求成功
//                        L.i("数据请求成功：${it.body}" )
//                        val s = jsonObject.getJSONObject("data").toString()
//                        L.e("data:" + s)
                        var o = GsonUtil.GsonToBean(it.body, LoginResBody::class.java)
//                        L.e("o--$o")
//                        L.e("listener--$listener")

                        listener.onSuccess(o!!)
                    } else {//请求失败
                        listener.onError(errBean.des!!)
                    }

                },{
            L.e("数据请求失败: $it")
            it.toString()
            listener.onError(it.toString())

        },{
            //            L.i("mvp--complete")
        }
        )

    }

}