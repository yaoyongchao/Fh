package com.lottchina.xdbao.ui.story.personalstores

import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.lottchina.baselib.base.BaseActivity
import com.lottchina.baselib.ui.fragment.ZoomImageFragment
import com.lottchina.baselib.utils.ImgUtilPc
import com.lottchina.cplib.data.bean.BindingStationBean
import com.lottchina.xdbao.R
import com.lottchina.xdbao.net.CpApi
import com.lottchina.xdbao.utils.CommonUtil
import com.vcaidian.customer.utils.RouteUrl
import kotlinx.android.synthetic.main.activity_personal_stores.*

/**
 * @Author: Austin
 * @Date: 19-5-8
 * @Description: 我的彩店
 */

@Route(path = RouteUrl.personalStores)
class PersonalStoresActivity : BaseActivity() {
    private lateinit var station: BindingStationBean
    override fun layoutId(): Int {
        return R.layout.activity_personal_stores
    }

    override fun initView() {
        setToolbarTitle(getString(R.string.personal_stores))
        customToolBar.setRightIcon(R.drawable.ic_share)
        showRightIcon(View.VISIBLE)
        station = CommonUtil.getStation()
        cv_phone.setTvTips(station?.tel_phone)
        cv_owner_name.setTvTips(station?.name)
        cv_store_name.setTvTips(station?.nickname)
        cv_store_address.setTvTips(station?.addr)
        cv_store_description.setTvTips(station?.descrip)
        ImgUtilPc.loadImgFace(mContext, CpApi.CP_FILE_URL+station?.face_pic_id,cv_store_picture.getRightImageView())

        cv_store_picture.getRightImageView().setOnClickListener {
//            ImgUtilPc.zoomImg(mContext, CpApi.CP_FILE_URL+station?.face_pic_id,cv_store_picture.getRightImageView())
            ZoomImageFragment.instance(CpApi.CP_FILE_URL+station?.face_pic_id).show(supportFragmentManager,"zoomimage")
        }

    }

    override fun initData() {
    }

    override fun initListener() {
    }

    override fun onClickRight(view: View) {
        super.onClickRight(view)
        toast("分享")
    }


}
