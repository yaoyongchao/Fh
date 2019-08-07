package com.lottchina.xdbao.ui.home

import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.facade.annotation.Route
import com.lottchina.baselib.base.BaseActivity
import com.lottchina.cplib.data.bean.StatusEvent
import com.lottchina.cplib.data.bean.Terminal
import com.lottchina.xdbao.R
import com.lottchina.xdbao.net.CpSocketUtil
import com.lottchina.xdbao.protocol.message.StationMessage
import com.lottchina.xdbao.ui.common.MyRxStatus
import com.lottchina.xdbao.ui.lotterybuyer.LotteryBuyerFragment
import com.lottchina.xdbao.ui.story.StoreFragment
import com.lottchina.xdbao.ui.ticket.TicketsFragment
import com.lottchina.xdbao.utils.CommonUtil
import com.vcaidian.customer.utils.RouteUrl
import com.vcaidian.wclib.base.BaseFragment
import com.vcaidian.wclib.utils.TabUtils
import gorden.rxbus2.Subscribe
import gorden.rxbus2.ThreadMode
import kotlinx.android.synthetic.main.activity_home.*


@Route(path = RouteUrl.home)
class HomeActivity : BaseActivity(), ViewPager.OnPageChangeListener , CpSocketUtil.MessageListener{

    private  var tabTitles = mutableListOf(com.lottchina.xdbao.R.string.tab1, com.lottchina.xdbao.R.string.tab2, com.lottchina.xdbao.R.string.tab3)
    private  var tabImgs = mutableListOf(com.lottchina.xdbao.R.drawable.tab1_selector, com.lottchina.xdbao.R.drawable.tab2_selector, com.lottchina.xdbao.R.drawable.tab3_selector)
    private  var tabFragments = mutableListOf<BaseFragment>()
    private lateinit var homePagerAdapter: HomePagerAdapter
    private lateinit var terminal: Terminal
    override fun layoutId(): Int {
        return com.lottchina.xdbao.R.layout.activity_home
    }

    override fun initView() {
        showToolbar(View.GONE)
        CpSocketUtil.getInstance().startSocketComm(CommonUtil.getUserId(), CommonUtil.getToken(),this)
//        CpSocketUtil.instance.startSocketLink(11,"22")
        terminal = CommonUtil.getTerminal()
        initViewPager()
    }

    override fun onResume() {
        super.onResume()

//        if (tabTitles.size > 2) {
//            tabImgs.removeAt(1)
//            tabTitles.removeAt(1)
//            tabFragments.removeAt(1)
//            TabUtils.setTabsImg(tablayout,layoutInflater,tabTitles,tabImgs)
//            homePagerAdapter.notifyDataSetChanged()
//        }

//        tabFragments




    }

    @Subscribe(code = MyRxStatus.SOCKET_LINK, threadMode = ThreadMode.MAIN)
    fun receiveSocketSucceeded(statusEvent: StatusEvent) {
        Log.e("aa","Socket链接状态：" + statusEvent.status)
        setSocketStatus()
    }

    fun setSocketStatus() {
        val isSocketConnected = CpSocketUtil.getInstance().isSocketConnected
        tv_socket_indicato_light.setBackgroundResource(if (isSocketConnected) R.drawable.bg_socket_status_on else R.drawable.bg_socket_status_off)
        tv_socket_status.setText(if (isChangingConfigurations) "在线" else "离线")
        tv_code.setText(terminal?.code)
    }

//    @Subscribe(code = MyRxStatus.SOCKET_LINK_FAILED, threadMode = ThreadMode.MAIN)
//    fun receiveSocketFAILED () {
//    }

    private fun initViewPager() {
//        tabTitles = arrayOf(R.string.tab1,R.string.tab2,R.string.tab3)
//        tabImgs = arrayOf(R.drawable.tab1_selector,R.drawable.tab2_selector,R.drawable.tab3_selector)
//        tabFragments = arrayOf(TicketsFragment.instance,LotteryBuyerFragment.instance,StoreFragment.instance)
        tabFragments.add(TicketsFragment.instance)
        tabFragments.add(LotteryBuyerFragment.instance)
        tabFragments.add(StoreFragment.instance)
        homePagerAdapter = HomePagerAdapter(supportFragmentManager)
//        TabUtils.setTabsImg(tablayout,layoutInflater,tabTitles,tabImgs)
        viewpager.adapter = homePagerAdapter
        TabUtils.setTabsImg(tablayout,layoutInflater,tabTitles,tabImgs)
        homePagerAdapter.notifyDataSetChanged()

    }

    override fun initData() {
    }

    override fun initListener() {
        viewpager.setScroll(true)
        viewpager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tablayout))
        tablayout.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(viewpager))
        viewpager.setOnPageChangeListener(this)
        viewpager.offscreenPageLimit = 2
    }

    override fun isFullScreen(): Boolean {
        return false
    }

    /**
     * @description: ViewPager 适配器
     */
    inner class HomePagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return tabFragments.get(position)
        }

        override fun getCount(): Int {
            return tabFragments.size
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        }

    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        llyt_socket_status.visibility = if (position == 0) View.VISIBLE else View.GONE

    }
    override fun onMessage(msgList: MutableList<StationMessage>?) {

    }

}
