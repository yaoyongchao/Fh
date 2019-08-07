package com.lottchina.xdbao.ui.home

import com.lottchina.baselib.mvp.BaseView
import com.lottchina.cplib.data.base.BaseRequestBody
import com.lottchina.cplib.data.bean.body.login.LoginResBody
import com.lottchina.cplib.data.bean.body.login.StoreResBody
import com.lottchina.xdbao.mvp.BaseMvpModel
import com.vcaidian.baselib.mvp.BaseModel
import com.vcaidian.wclib.mvp.BasePresenter
import com.vcaidian.wclib.mvp.MVPListener

/**
 * Author: Austin
 * Date: 19-3-29
 * Description:
 */
interface HomeContract {
    interface HomeView: BaseView {
        fun bindTerminalSuccess(station: LoginResBody)
        fun bindTerminalFailed(station: LoginResBody)
        fun loadStoreSuccess(store: StoreResBody)
    }

    abstract class HomePresenter<M: BaseModel>: BasePresenter<HomeContract.HomeView,M>() {
        abstract fun bindTerminal(userId:Int,code: String,password: String,stationId: Int)
        abstract fun loadStore()
    }

    abstract class HomeModel: BaseMvpModel() {
        abstract fun bindTerminal(userId:Int, body: BaseRequestBody, listener: MVPListener<LoginResBody>)
//        abstract fun loadUserDetails(account: Int,channel: Int,customer: Int,station: Int,terminal: Int, listener: MVPListener<LoginResBody>)
        abstract fun loadStore(listener: MVPListener<StoreResBody>)
    }
}