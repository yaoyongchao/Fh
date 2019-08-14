package com.ygfh.doctor.ui.demo

import com.fh.baselib.http.BaseObserver
import com.fh.baselib.utils.L
import com.fh.baselib.utils.rx.MyRxScheduler
import com.ygfh.doctor.data.bean.Login
import com.ygfh.doctor.net.DcServiceFactory

/**
 * Author: YongChao
 * Date: 19-8-14 下午3:29
 * Description:
 */
class TwoPresenter: TwoContract.TwoPresenter() {
    override fun login() {
        DcServiceFactory.getService().login2()
                .compose(MyRxScheduler.ioMain())
                .subscribe(object : BaseObserver<Login>(){
                    override fun onSuccess(t: Login?) {
                        L.e("login:" + t!!)
                        getView()?.loginSuccess()
                    }
                })

    }

}