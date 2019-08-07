package com.vcaidian.wclib.mvp

import android.os.Bundle
import com.lottchina.baselib.base.BaseActivity
import com.lottchina.baselib.mvp.BaseView
import com.vcaidian.baselib.utils.CreatUtil

abstract class MvpBaseActivity<V: BaseView,P : BasePresenter<V,*> >: BaseActivity(){
    var mPresenter: P? =null//可空类型


    override fun onCreate(savedInstanceState: Bundle?) {
        mPresenter = CreatUtil.getT(this,1)
        super.onCreate(savedInstanceState)
//        L.e( "mPresenter: $mPresenter")
        mPresenter?.bindView(this as V)

    }

    /*override fun initView() {

    }*/



    override fun initData() {
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.unBindView()
    }
}
