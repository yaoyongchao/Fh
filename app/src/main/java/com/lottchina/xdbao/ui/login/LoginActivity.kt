package com.lottchina.xdbao.ui.login

import android.text.TextUtils
import android.util.Log
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.lottchina.baselib.utils.L
import com.lottchina.cplib.data.bean.BindingStationBean
import com.lottchina.cplib.data.bean.body.login.LoginResBody
import com.lottchina.cplib.data.bean.body.login.StoreResBody
import com.lottchina.xdbao.R
import com.lottchina.xdbao.ui.dialog.TermialsDialogFragment
import com.lottchina.xdbao.utils.CommonUtil
import com.vcaidian.customer.utils.JumpUtil
import com.vcaidian.customer.utils.RouteUrl
import com.vcaidian.wclib.mvp.MvpBaseActivity
import com.vcaidian.wclib.utils.ActivityManager
import kotlinx.android.synthetic.main.activity_login.*


/**
 * @Author: Austin
 * @Date: 19-3-29
 * @Description: 登录页
 */
@Route(path = RouteUrl.login)
class LoginActivity : MvpBaseActivity<LoginContract.LoginView,LoginPresenter>(),LoginContract.LoginView,View.OnClickListener,TermialsDialogFragment.MyOnItemClickListener {

    @Autowired(name = "bean")
    lateinit var bean: BindingStationBean
    lateinit var termialsDialogFragment: TermialsDialogFragment


    override fun layoutId(): Int {
        return R.layout.activity_login
    }

    override fun initView() {
        showToolbar(View.GONE)
        ARouter.getInstance().inject(this)
        L.d("传递过来的bean：" + bean)

        tv_shop_name.text = bean?.nickname
        tv_owner_name.text = bean?.name
        cedt_selected.setText(bean?.terminals?.get(0)?.code)
        cedt_selected.showClearIconVisible(false)
        cedt_password.setText("000000")

        termialsDialogFragment = TermialsDialogFragment(bean.getTerminals())
    }

    override fun initData() {
    }

    override fun initListener() {
        /*btn.setOnClickListener {

            *//*CommomDialog(mContext, com.lottchina.xdbao.R.style.dialog,"您确定删除此信息？", CommomDialog.OnCloseListener { dialog, confirm ->
                Log.e("aa","11111111")

            }).setTitle("提示").show()*//*

            TermialsDialogFragment().show(supportFragmentManager,"dialog")
        }*/

        llyt_selected.setOnClickListener(this)
        cedt_selected.setOnClickListener(this)
        btn_login.setOnClickListener(this)

        termialsDialogFragment.setMyOnItemClickListener(this)
    }

    override fun onItemClick(v: View, i: Int) {
        cedt_selected.setText(bean?.terminals?.get(i)?.code)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.cedt_selected,
            R.id.llyt_selected ->
                termialsDialogFragment?.show(supportFragmentManager,"selectedTerminal")
            R.id.btn_login ->
                login()
        }
    }

    private fun login() {
        if (TextUtils.isEmpty(cedt_selected.text)) {
            toast(getString(R.string.login_not_empty))
        } else if (TextUtils.isEmpty(cedt_password.text)) {
            toast(getString(R.string.password_not_empty))
        } else {
            L.i("点击登录按钮")
            showDialog()
            mPresenter?.login(bean.id,cedt_selected.text.toString(),cedt_password.text.trim().toString(),bean?.id)
        }
    }


    override fun isFullScreen(): Boolean {
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        ActivityManager.instance.AppExit()
    }

    override fun loginSuccess(body: LoginResBody) {
        CommonUtil.saveUserIdToken(body.user_id,body.token)
        CommonUtil.saveTerminal(body.terminal)
        Log.e("aa","---保存信息成功")
        mPresenter?.loadStore()
    }

    override fun loadFailure(fail: String?) {
        dismissDialog()
    }

    override fun loadStoreSuccess(store: StoreResBody) {
//        dismissDialog()
        Log.e("aa","获取店铺信心成功")
        CommonUtil.saveStation(bean)
//        CommonUtil.saveCustomer(store.customer)
        JumpUtil.jumpActivity(RouteUrl.home)
    }

    override fun onStop() {
        super.onStop()
        dismissDialog()
    }
}
