package com.ygfh.doctor.ui.home

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.ViewGroup
import com.alibaba.android.arouter.facade.annotation.Route
import com.fh.baselib.base.BaseActivity
import com.fh.baselib.base.BaseFragment
import com.fh.baselib.utils.TabUtils
import com.fh.cplib.utils.RouteUrl
import com.ygfh.doctor.R
import kotlinx.android.synthetic.main.activity_home.*
@Route(path = RouteUrl.home)
class HomeActivity : BaseActivity() , ViewPager.OnPageChangeListener{


    private  var tabTitles = mutableListOf(R.string.tab1, R.string.tab2, R.string.tab3)
    private  var tabImgs = mutableListOf(R.drawable.tab2_selector, R.drawable.tab2_selector, R.drawable.tab2_selector)
    private  var tabFragments = mutableListOf<BaseFragment>()
    private lateinit var homePagerAdapter: HomePagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun layoutId(): Int {
        return R.layout.activity_home
    }

    override fun initView() {
        showToolbar(false)
        tabFragments.add(Tab1Fragment.instance)
        tabFragments.add(Tab2Fragment.instance)
        tabFragments.add(Tab3Fragment.instance)

        homePagerAdapter = HomePagerAdapter(supportFragmentManager)
        viewpager.adapter = homePagerAdapter
        TabUtils.setTabsImg(tablayout,layoutInflater,tabTitles,tabImgs)
        homePagerAdapter.notifyDataSetChanged()



    }

    override fun isFullScreen(): Boolean {
        return true
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

    override fun onPageScrollStateChanged(p0: Int) {

    }

    override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
    }

    override fun onPageSelected(p0: Int) {
    }
}

