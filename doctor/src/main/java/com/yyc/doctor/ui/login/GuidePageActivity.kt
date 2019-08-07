package com.yyc.doctor.ui.login

import android.widget.ImageView
import cn.bingoogolapple.bgabanner.BGABanner
import cn.bingoogolapple.bgabanner.BGALocalImageSize
import com.alibaba.android.arouter.launcher.ARouter
import com.lottchina.baselib.base.BaseActivity
import com.lottchina.baselib.utils.L
import com.yyc.doctor.R
import com.yyc.doctor.utils.CommonUtil
import kotlinx.android.synthetic.main.activity_guide_page.*

/**
 * Author: YongChao
 * Date: 19-8-7 下午3:41
 * Description: 引导页
 */
//@Route(path = RouteUrl.guide)
class GuidePageActivity : BaseActivity() {
    override fun layoutId(): Int {
        return R.layout.activity_guide_page
    }

    override fun initView() {
        L.e("--" + CommonUtil.isFirstUse())
        /*if (!CommonUtil.isFirstUse()) {
            JumpUtil.jumpActivity(RouteUrl.login)
            finish()
        }*/
        showToolbar(false)


        processLogic()



    }

    override fun isFullScreen(): Boolean {
        return true
    }

    override fun initData() {
    }

    override fun initListener() {
        /**
         * 设置进入按钮和跳过按钮控件资源 id 及其点击事件
         * 如果进入按钮和跳过按钮有一个不存在的话就传 0
         * 在 BGABanner 里已经帮开发者处理了防止重复点击事件
         * 在 BGABanner 里已经帮开发者处理了「跳过按钮」和「进入按钮」的显示与隐藏
         */
        banner_guide.setEnterSkipViewIdAndDelegate(R.id.btn_guide_enter, R.id.tv_guide_skip, BGABanner.GuideDelegate {
            CommonUtil.saveFirstUse()
//            JumpUtil.jumpActivity(RouteUrl.login)
            ARouter.getInstance().build("/yyc/ab").navigation()
//            ActivityManager.instance.finishActivity(this)
        })
    }

    private fun processLogic() {
        // Bitmap 的宽高在 maxWidth maxHeight 和 minWidth minHeight 之间
        val localImageSize = BGALocalImageSize(720, 1280, 320f, 640f)
        // 设置数据源
        banner_guide.setData(localImageSize, ImageView.ScaleType.CENTER_CROP,
                R.drawable.ic_guide1,
                R.drawable.ic_guide2,
                R.drawable.ic_guide3
        )
    }


}
