package com.lottchina.xdbao.ui.story

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.lottchina.baselib.base.BaseActivity
import com.vcaidian.customer.utils.RouteUrl
import kotlinx.android.synthetic.main.activity_about_us.*

@Route(path = RouteUrl.aboutUs)
class AboutUsActivity : BaseActivity() {
    override fun layoutId(): Int {
        return com.lottchina.xdbao.R.layout.activity_about_us
    }

    @SuppressLint("JavascriptInterface")
    override fun initView() {
        setToolbarTitle(getString(com.lottchina.xdbao.R.string.about_us))
        webView.settings.javaScriptEnabled = true
        webView.settings.defaultTextEncodingName = "UTF-8"
        webView.addJavascriptInterface(JsObject(), "MobileLaw")
        webView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        webView.loadUrl("https://i.eqxiu.com/s/1t9eMtXF")

    }

    override fun initData() {
    }

    override fun initListener() {
    }

    inner class JsObject{
        fun call(phnum: String) {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phnum"))
            startActivity(intent)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        webView.removeAllViews()
        webView.destroy()

    }

}
