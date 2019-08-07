package com.lottchina.xdbao.ui.story

import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.lottchina.baselib.base.BaseActivity
import com.lottchina.baselib.utils.AppUtil
import com.lottchina.xdbao.R
import com.vcaidian.customer.utils.JumpUtil
import com.vcaidian.customer.utils.RouteUrl
import kotlinx.android.synthetic.main.activity_product_infor.*

/**
 * @Author: Austin
 * @Date: 19-5-8
 * @Description: 产品信息
 */
@Route(path = RouteUrl.productInfor)
class ProductInforActivity : BaseActivity() {
    override fun layoutId(): Int {
        return R.layout.activity_product_infor
    }

    override fun initView() {
        setToolbarTitle(getString(R.string.product_information))
        cv_version_check.setTvTips("当前版本 V" + AppUtil.getVersionName(mContext))
        cv_version_check.setOnClickListener(this)
        cv_about_us.setOnClickListener(this)

    }

    override fun initData() {
    }

    override fun initListener() {
    }

    override fun onClick(v: View?) {
        super.onClick(v)

        when(v?.id) {
            R.id.cv_about_us ->
                JumpUtil.jumpActivity(RouteUrl.aboutUs)
            R.id.cv_version_check ->
                toast("检查版本号")
        }

    }

}
