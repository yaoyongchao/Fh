package com.lottchina.xdbao.ui.lotterybuyer

import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.lottchina.baselib.adapter.BaseAdapter
import com.lottchina.baselib.widget.MyDivider
import com.lottchina.xdbao.R
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.vcaidian.wclib.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_lottery_friend.view.*
import kotlinx.android.synthetic.main.item_lottery_friend.view.*

/**
 * Author: Austin
 * Date: 19-5-8
 * Description:彩友列表
 */
class LotteryFriendFragment: BaseFragment() {


    private lateinit var adapter : BaseAdapter<String>
    private var list = mutableListOf<String>("张三","张三1","张三2")
    override fun layoutId(): Int {
        return R.layout.fragment_lottery_friend
    }

    override fun isRefreshView(): Boolean {
        return true
    }

    override fun initView() {
        rootView.rv_friend.layoutManager = LinearLayoutManager(mContext)
        rootView.rv_friend.addItemDecoration(MyDivider(mContext,DividerItemDecoration.VERTICAL))
        adapter = BaseAdapter(R.layout.item_lottery_friend,list){ itemView: View, bean: String, i: Int ->
            itemView.tv.setText(bean)

        }
        rootView.rv_friend.adapter = adapter

    }

    override fun initListener() {
    }

    override fun initData() {
    }

}