package com.lottchina.xdbao.ui.ticket.order

import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Route
import com.lottchina.baselib.base.BaseActivity
import com.lottchina.baselib.ui.fragment.DropDownFragment
import com.lottchina.baselib.utils.L
import com.lottchina.xdbao.R
import com.lottchina.xdbao.utils.CommonUtil
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.vcaidian.customer.utils.RouteUrl
import kotlinx.android.synthetic.main.activity_order_list.*

/**
 * @Author: Austin
 * @Date: 19-5-9
 * @Description: 订单出票列表
 */
@Route(path = RouteUrl.orderList)
class OrderListActivity : BaseActivity() , OnRefreshLoadMoreListener {
    private var listSort = ArrayList<String>()
    private var listFilter = ArrayList<String>()
    private lateinit var dropDownFragment: DropDownFragment


    override fun layoutId(): Int {
        return R.layout.activity_order_list
    }

    override fun initView() {
        setToolbarTitle(getString(R.string.order_ticket))
//        addFragment(R.id.flyt_content,OrderListFragment())
        listSort.add("截期时间")
        listSort.add("金额大")
        listFilter.add("默认")
        listFilter.add("竞彩")
        listFilter.add("数字彩")
        listFilter.add("传统足球")
        listFilter.add("高频彩")
        listFilter.add("福彩")
        dropDownFragment = DropDownFragment.instance("排序","筛选",listSort,listFilter)
        dropDownFragment.setOnItemClickListener(object : DropDownFragment.OnItemClickListener{
            override fun onItemClick(txtPosition: Int, position1: Int, position2: Int) {
                Log.e("aa","--$txtPosition-----${listFilter.get(position1)} --------------${listFilter.get(position2)}")
            }
        })
        addFragment(R.id.llyt_drop,dropDownFragment)


        initrv()
    }

    fun initrv() {
        CommonUtil.setRecyclerView(rv,mContext)
        
    }

    override fun initData() {
    }

    override fun initListener() {
        smartrefresh.setEnableRefresh(true)
        smartrefresh.setEnableLoadMore(true)
        smartrefresh.setOnRefreshListener(this)
        smartrefresh.setOnLoadMoreListener(this)
    }

    override fun onLoadMore(refreshLayout: RefreshLayout?) {
        L.i("加载更多")
    }

    override fun onRefresh(refreshLayout: RefreshLayout?) {
        L.i("下拉刷新")
    }
}
