package com.lottchina.xdbao.ui.login

import com.lottchina.cplib.data.bean.body.login.LoginReqBody
import com.lottchina.cplib.data.bean.body.login.LoginResBody
import com.lottchina.cplib.data.bean.body.login.StoreResBody
import com.vcaidian.wclib.mvp.MVPListener

/**
 * Author: Austin
 * Date: 19-4-10
 * Description:
 */
class LoginPresenter : LoginContract.LoginPresenter<LoginModel>() {

    override fun login(userId:Int,code: String,password: String,stationId: Int) {
        var body = LoginReqBody()
        body.code = code
        body.login_pass = password
        body.station_id = stationId
        mModel?.login(userId,body,object : MVPListener<LoginResBody>{
            override fun onSuccess(data: LoginResBody) {
                obtainView()?.loginSuccess(data)
            }

            override fun onError(error: String) {
                obtainView()?.loadFailure(error)
            }
        })

    }

    override fun loadStore() {
        mModel?.loadStore(object : MVPListener<StoreResBody>{
            override fun onSuccess(data: StoreResBody) {
                obtainView()?.loadStoreSuccess(data)
            }

            override fun onError(error: String) {
                obtainView()?.loadFailure(error)
            }

        })

    }

}