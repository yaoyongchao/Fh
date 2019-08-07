package com.lottchina.baselib.ui.fragment

import android.os.Bundle
import com.lottchina.baselib.R
import com.lottchina.baselib.base.BaseDialogFragment
import com.lottchina.baselib.utils.ImgUtilPc
import com.lottchina.baselib.utils.ScreenUtils
import kotlinx.android.synthetic.main.fragment_zoom_image.view.*

/**
 * Author: Austin
 * Date: 19-5-8
 * Description:
 */
class ZoomImageFragment: BaseDialogFragment() {

    lateinit var url : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        url = arguments?.getString("url")!!
        cancelTouchOutside = true
        dialogWidth = ScreenUtils.dip2px(context,20f)
        cancelableOnKeyBack = true
        setBackGroundDrawableResource(R.drawable.bg_layout_selector)
    }

    companion object {
        fun instance(url:String):ZoomImageFragment {
            var zoomImageFragment = ZoomImageFragment()
            var bundle = Bundle()
            bundle.putString("url",url)
            zoomImageFragment.arguments = bundle
            return zoomImageFragment
        }
    }

    override fun layoutId(): Int {
        return R.layout.fragment_zoom_image
    }

    override fun initVies() {
        ImgUtilPc.loadImgFace(context, url,dailogView.img_zoom)
        dailogView.img_zoom.setOnClickListener {
            dismiss()
        }

    }
}