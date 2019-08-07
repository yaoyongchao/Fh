package com.lottchina.xdbao.ui.story.beep

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import com.alibaba.android.arouter.facade.annotation.Route
import com.lottchina.baselib.base.BaseActivity
import com.lottchina.xdbao.R
import com.vcaidian.customer.utils.RouteUrl
import kotlinx.android.synthetic.main.activity_beep_setting.*

/**
 * Author: Austin
 * Date: 19-5-6
 * Description: 提示音设置
 */
@Route(path = RouteUrl.beepSetting)
class BeepSettingActivity : BaseActivity() ,CompoundButton.OnCheckedChangeListener {


    override fun layoutId(): Int {
        return R.layout.activity_beep_setting
    }

    override fun initView() {
        setToolbarTitle(getString(R.string.beep_setting))
    }

    override fun initData() {

    }

    override fun initListener() {
        pending_payment.setOnCheckedChangeListener(this)
        pending_payment.setOnClickListener(this)
        waiting_for_ticket_order.setOnCheckedChangeListener(this)
        waiting_for_ticket_order.setOnClickListener(this)

    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        when (buttonView?.id) {
            R.id.pending_payment ->
                setPending()
            R.id.waiting_for_ticket_order ->
                setWaitingOrder()
        }

    }

    override fun onClick(v: View?) {
        super.onClick(v)

    }

    fun setPending() {
        toast("待收款方案")
    }

    fun setWaitingOrder() {
        toast("待出票订单")
    }

}
