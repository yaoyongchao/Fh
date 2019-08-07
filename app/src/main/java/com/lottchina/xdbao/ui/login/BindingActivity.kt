package com.lottchina.xdbao.ui.login

import android.text.Editable
import android.text.TextUtils
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.lottchina.baselib.utils.L
import com.lottchina.cplib.data.bean.BindingStationBean
import com.lottchina.cplib.utils.Validator
import com.lottchina.xdbao.R
import com.vcaidian.customer.utils.JumpUtil
import com.vcaidian.customer.utils.RouteUrl
import com.vcaidian.wclib.mvp.MvpBaseActivity
import kotlinx.android.synthetic.main.activity_binding.*

/**
 * @Author: Austin
 * @Date: 19-3-29
 * @Description: 店铺绑定，绑定用户手机号
 */
@Route(path = RouteUrl.binding)
class BindingActivity : MvpBaseActivity<BindingContract.BindingView,BindingPresenter>(),BindingContract.BindingView {


    override fun layoutId(): Int {
        return R.layout.activity_binding
    }

    override fun initView() {
        showToolbar(View.GONE)
        edt_phone.text = Editable.Factory.getInstance().newEditable("18039364720")
    }

    override fun initData() {
    }

    override fun initListener() {
        btn_binding.setOnClickListener {
            bindingShop()
        }

    }

    private fun bindingShop() {
        if (TextUtils.isEmpty(edt_phone.text)) {
            toast(getString(R.string.phone_not_empty))
        } else if (!Validator.isValidNumber(edt_phone.text.toString().trim())) {
            toast(getString(R.string.error_phone))
        } else {
            showDialog()
            mPresenter?.bindingShop(edt_phone.text.toString())
//            test()
        }
    }

    override fun isFullScreen(): Boolean {
        return true
    }

    override fun bindingSuccess(station: BindingStationBean) {
        dismissDialog()
        L.i("数据请求成功：" + station);
        JumpUtil.jumpActivityWithParcelable(RouteUrl.login,station)




//        startActivity(Intent(this,LoginActivity::class.java))
//        ARouter.getInstance().build(RouteUrl.login)
//                .withParcelable("login",station)
//                .navigation()

    }

    override fun loadFailure(fail: String?) {
        dismissDialog()
    }

    fun test() {
        var map :HashMap<Int,String>  = HashMap()
        map.put(1,"你好")
        L.e(map.get(1)!!)
    }
}
