package com.lottchina.xdbao.ui.login

import com.lottchina.baselib.utils.L
import com.lottchina.cplib.data.bean.BindingStationBean
import com.lottchina.cplib.data.bean.body.login.BindingRequBody
import com.vcaidian.wclib.mvp.MVPListener

/**
 * Author: Austin
 * Date: 19-4-10
 * Description:
 */
class BindingPresenter : BindingContract.BindingPresenter<BindingModel>() {
    override fun bindingShop(phoneNumber: String) {
        var body = BindingRequBody();
        body.username = phoneNumber;
        mModel?.bindingShop(phoneNumber,body,object : MVPListener<BindingStationBean>{
            override fun onSuccess(data: BindingStationBean) {
                L.i("BindingPresenter--数据请求成功:$data")
                obtainView()?.bindingSuccess(data)
            }

            override fun onError(error: String) {
                obtainView()?.loadFailure(error)
            }
        })

    }

}