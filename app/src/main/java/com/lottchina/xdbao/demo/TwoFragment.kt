package com.lottchina.xdbao.demo

import com.lottchina.xdbao.R
import com.vcaidian.wclib.base.BaseFragment

/**
 * Author: Austin
 * Date: 19-3-28
 * Description:
 */
class TwoFragment : BaseFragment() {
    override fun layoutId(): Int {
        return R.layout.fragment_two

    }

    override fun initView() {

    }

    override fun isRefreshView(): Boolean {
        return true
    }

    override fun initListener() {
    }

    override fun initData() {
    }

}