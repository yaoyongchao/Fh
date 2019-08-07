package com.lottchina.xdbao.ui.story.settings

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.lottchina.baselib.base.BaseActivity
import com.lottchina.xdbao.R
import com.vcaidian.customer.utils.RouteUrl

@Route(path = RouteUrl.printerSetting)
class PrinterSettingActivity : BaseActivity() {
    override fun layoutId(): Int {
        return R.layout.activity_printer_setting
    }

    override fun initView() {
        setToolbarTitle("投注单打印机")
    }

    override fun initData() {
    }

    override fun initListener() {
    }

}
