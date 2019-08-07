package com.lottchina.xdbao.ui.story.settings

import android.util.Log
import android.view.View
import android.widget.CompoundButton
import com.alibaba.android.arouter.facade.annotation.Route
import com.lottchina.baselib.base.BaseActivity
import com.lottchina.xdbao.R
import com.vcaidian.customer.utils.JumpUtil
import com.vcaidian.customer.utils.RouteUrl
import kotlinx.android.synthetic.main.activity_settings.*

/**
 * @Author: Austin
 * @Date: 19-5-8
 * @Description: 参数设置
 */
@Route(path = RouteUrl.settings)
class SettingsActivity : BaseActivity(),CompoundButton.OnCheckedChangeListener {


    override fun layoutId(): Int {
        return R.layout.activity_settings
    }

    override fun initView() {
        setToolbarTitle(getString(R.string.parameter_settings))
    }

    override fun initData() {
    }

    override fun initListener() {
        ask_ticket.setOnCheckedChangeListener(this)
        printer.setOnClickListener(this)
        beep.setOnClickListener(this)
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        Log.e("aa", "--" + isChecked )

    }

    override fun onClick(v: View?) {
        super.onClick(v)
        when(v?.id) {
            R.id.printer->{
                JumpUtil.jumpActivity(RouteUrl.printerSetting)
            }
            R.id.beep -> {
                JumpUtil.jumpActivity(RouteUrl.beepSetting)
            }
        }

    }

    override fun isFullScreen(): Boolean {
        return false
    }
}
