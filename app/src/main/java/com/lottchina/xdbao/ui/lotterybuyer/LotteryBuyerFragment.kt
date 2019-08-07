package com.lottchina.xdbao.ui.lotterybuyer

import android.util.Log
import android.view.View
import com.lottchina.baselib.ui.fragment.DropDownFragment
import com.lottchina.xdbao.R
import com.vcaidian.wclib.base.BaseFragment
import java.util.*


/**
 * Author: Austin
 * Date: 19-5-6
 * Description:
 */
class LotteryBuyerFragment : BaseFragment(),View.OnClickListener {
    private lateinit var dropDownFragment: DropDownFragment
    private var listSort = ArrayList<String>()
    private var listFilter = ArrayList<String>()
    private lateinit var arr1 : ArrayList<String>
    private lateinit var arr2 : ArrayList<String>

    companion object {
        // 单例模式： 双重校验锁式
        val instance: LotteryBuyerFragment by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            LotteryBuyerFragment()
        }
    }


    override fun layoutId(): Int {
        return com.lottchina.xdbao.R.layout.fragment_lottery_buyer
    }

    override fun initView() {
        listSort.add("默认")
        listSort.add("活跃时间由近到远")
        listSort.add("活跃时间由远到近")
        listSort.add("到店时间由近到远")
        listSort.add("到店时间由远到近")
        listSort.add("账户余额由多到少")
        listSort.add("账户余额由少到多")
        listFilter.add("全部")
        listFilter.add("到店彩友")
        listFilter.add("离店彩友")
        listFilter.add("取消关注彩友")
        dropDownFragment = DropDownFragment.instance("排序","筛选",listSort,listFilter)
        dropDownFragment.setOnItemClickListener(object : DropDownFragment.OnItemClickListener{
            override fun onItemClick(txtPosition: Int, position1: Int, position2: Int) {
                when (txtPosition) {
                    1 -> {
                        Log.e("aa","--$txtPosition-----${listSort.get(position1)} --------------${listFilter.get(position2)}")
                    }
                    2 -> {
                        Log.e("aa","--$txtPosition-----${listSort.get(position1)} --------------${listFilter.get(position2)}")
                    }
                }
            }
        })
        dropDownFragment.setBottomMarginVisibility(View.VISIBLE)
        addFragment(R.id.llyt_drop,dropDownFragment)

        addFragment(R.id.llyt_friend,LotteryFriendFragment())



    }


    override fun initListener() {

    }

    override fun initData() {

    }
    override fun onClick(v: View?) {
        when(v?.id) {

        }

    }

}