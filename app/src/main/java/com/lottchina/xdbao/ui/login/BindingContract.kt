package com.lottchina.xdbao.ui.login

import com.lottchina.baselib.mvp.BaseView
import com.lottchina.cplib.data.base.BaseRequestBody
import com.lottchina.cplib.data.bean.BindingStationBean
import com.vcaidian.baselib.mvp.BaseModel
import com.vcaidian.wclib.mvp.BasePresenter
import com.vcaidian.wclib.mvp.MVPListener

/**
 * Author: Austin
 * Date: 19-3-29
 * Description:
 */
interface BindingContract {
    interface BindingView: BaseView {
        fun bindingSuccess(station: BindingStationBean)
    }

    abstract class BindingPresenter<M: BaseModel>: BasePresenter<BindingView,M>() {
        abstract fun bindingShop(phoneNumber: String)

    }

    abstract class BindingModel: BaseModel {
        abstract fun bindingShop(username: String, body: BaseRequestBody, listener: MVPListener<BindingStationBean>)
    }
}