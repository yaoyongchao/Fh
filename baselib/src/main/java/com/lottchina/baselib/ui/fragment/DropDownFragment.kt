package com.lottchina.baselib.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import com.lottchina.baselib.R
import com.lottchina.baselib.widget.DropdownPopupWindow
import com.vcaidian.wclib.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_drop_down.view.*

/**
 * Author: Austin
 * Date: 19-5-8
 * Description:
 */
class DropDownFragment: BaseFragment(),View.OnClickListener {


    private var list1 = ArrayList<String>()
    private var list2 = ArrayList<String>()
    private lateinit var dropdownPopupWindow1:DropdownPopupWindow
    private lateinit var dropdownPopupWindow2:DropdownPopupWindow
    private lateinit var str1: String
    private lateinit var str2: String
    private lateinit var onItemClickListener: OnItemClickListener
    private var popPosition1 = 0
    private var popPosition2 = 0
    private var visibilityBottomMargin = View.GONE
    override fun layoutId(): Int {
        return R.layout.fragment_drop_down
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        str1 = arguments?.getString("txt1")!!
        str2 = arguments?.getString("txt2")!!
        list1 = arguments?.getStringArrayList("list1")!!
        list2 = arguments?.getStringArrayList("list2")!!
    }

    companion object {
        fun instance(txt1:String,txt2:String,l: ArrayList<String>,l2: ArrayList<String>):DropDownFragment {
            var dropDownFragment = DropDownFragment()
            var bundle = Bundle()
            bundle.putString("txt1",txt1);
            bundle.putString("txt2",txt2);
            bundle.putStringArrayList("list1",l)
            bundle.putStringArrayList("list2",l2)
            dropDownFragment.arguments = bundle
            return dropDownFragment
        }
    }

    override fun initView() {
        rootView.tv_drop1.setText(str1)
        rootView.tv_drop2.setText(str2)

        dropdownPopupWindow1 = DropdownPopupWindow(mContext)
        dropdownPopupWindow2 = DropdownPopupWindow(mContext)
        dropdownPopupWindow1.setTvBottomVisibility(visibilityBottomMargin)
        dropdownPopupWindow2.setTvBottomVisibility(visibilityBottomMargin)
        dropdownPopupWindow1.setList(list1)
        dropdownPopupWindow2.setList(list2)



    }

    fun setBottomMarginVisibility(visibility: Int) {
        visibilityBottomMargin = visibility
    }

    override fun initListener() {
        rootView.llyt_drop1.setOnClickListener(this)
        rootView.llyt_drop2.setOnClickListener(this)

        dropdownPopupWindow1.setOnDismissListener {
            rootView.img_drop1.setImageResource(R.mipmap.ic_arrow_down)
        }
        dropdownPopupWindow2.setOnDismissListener {
            rootView.img_drop2.setImageResource(R.mipmap.ic_arrow_down)
        }

        dropdownPopupWindow1.setOnItemClickListener {
//            rootView.tv_drop1.setText(list1.get(it))
            popPosition1 = it
            onItemClickListener?.onItemClick(0,popPosition1,popPosition2)
        }
        dropdownPopupWindow2.setOnItemClickListener {
//            rootView.tv_drop2.setText(list2.get(it))
            popPosition2 = it
            onItemClickListener?.onItemClick(1,popPosition1,popPosition2)
        }
    }

    override fun initData() {

    }

    override fun onClick(v: View?) {
        Log.e("aa","22--")
        when(v?.id) {
            R.id.llyt_drop1 -> {
                dropdownPopupWindow1.showAsDropDown(rootView.llyt_drop)
                rootView.img_drop1.setImageResource(R.mipmap.ic_arrow_up)
            }
            R.id.llyt_drop2 -> {
                dropdownPopupWindow2.showAsDropDown(rootView.llyt_drop)
                rootView.img_drop2.setImageResource(R.mipmap.ic_arrow_up)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(txtPosition: Int,position1: Int,position2: Int)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }
}