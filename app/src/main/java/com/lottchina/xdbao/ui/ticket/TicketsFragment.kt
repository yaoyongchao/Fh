package com.lottchina.xdbao.ui.ticket

import android.util.Log
import android.view.View
import com.lottchina.xdbao.R
import com.vcaidian.customer.utils.JumpUtil
import com.vcaidian.customer.utils.RouteUrl
import com.vcaidian.wclib.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_tickets.view.*

/**
 * Author: Austin
 * Date: 19-5-6
 * Description:
 */
class TicketsFragment : BaseFragment(),View.OnClickListener {


    companion object {
        // 单例模式： 双重校验锁式
        val instance: TicketsFragment by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            TicketsFragment()
        }
    }

    override fun layoutId(): Int {
        return R.layout.fragment_tickets
    }

    override fun initView() {
    }

    override fun initListener() {
        rootView.cv_unpaid.setOnClickListener(this)
        rootView.cv_order.setOnClickListener(this)
        rootView.cv_billed.setOnClickListener(this)
        rootView.cv_award.setOnClickListener(this)
        rootView.cv_redemption.setOnClickListener(this)
        rootView.cv_refund.setOnClickListener(this)
    }

    override fun initData() {
    }

    override fun onClick(v: View?) {
        Log.e("aa","点击了")
        when (v?.id) {
            R.id.cv_unpaid -> toast("未支付")
            R.id.cv_order -> JumpUtil.jumpActivity(RouteUrl.orderList)
            R.id.cv_billed -> toast("已出单")
            R.id.cv_award -> JumpUtil.jumpActivity(RouteUrl.award)
            R.id.cv_redemption -> toast("对中奖票")
            R.id.cv_refund -> toast("退款")
        }

    }

}