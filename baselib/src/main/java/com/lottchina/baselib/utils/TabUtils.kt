package com.vcaidian.wclib.utils

import android.support.design.widget.TabLayout
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import com.lottchina.baselib.R
import kotlinx.android.synthetic.main.tab_custom.view.*

object TabUtils {
    /**
     * @description: 设置添加Tab 带图片
     */
    fun setTabsImg(tabLayout: TabLayout, inflater: LayoutInflater, tabTitlees: List<Int>, tabImgs: List<Int>) {
        for (i in tabImgs.indices) {
            val tab = tabLayout.newTab()
            val view = inflater.inflate(R.layout.tab_custom, null)
            tab.setCustomView(view)

            val tvTitle = view.tv_tab as TextView
            tvTitle.setText(tabTitlees[i])
            val imgTab = view.img_tab as ImageView
            imgTab.setImageResource(tabImgs[i])
            tabLayout.addTab(tab)

        }
    }

}