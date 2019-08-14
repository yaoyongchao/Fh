package com.ygfh.doctor.ui.demo

import com.fh.baselib.mvp.MvpBaseActivity
import com.fh.baselib.utils.L
import com.ygfh.doctor.R
import com.yyc.netlib.widget.LoadingFragment
import kotlinx.android.synthetic.main.activity_two.*

class TwoActivity : MvpBaseActivity<TwoContract.TwoView, TwoPresenter>(), TwoContract.TwoView {

    override fun loginSuccess() {
        L.d("登录成功--------")

    }

    override fun loadStoreSuccess() {

    }

    override fun layoutId(): Int {
        return R.layout.activity_two
    }

    override fun initView() {
        btn_login.setOnClickListener {
//            mPresenter = null
            //?.表示 不为空的时候执行
            mPresenter?.login()
        }

        btn_loading.setOnClickListener {
            LoadingFragment().show(supportFragmentManager,"loading")
//            LoadingFragment.instance.showw2(supportFragmentManager)
        }
    }

    override fun initListener() {

    }
}
