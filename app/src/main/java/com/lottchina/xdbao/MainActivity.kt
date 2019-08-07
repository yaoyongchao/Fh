package com.lottchina.xdbao

import android.content.Intent
import com.lottchina.baselib.base.BaseActivity
import com.lottchina.xdbao.demo.TwoActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getResources().getDrawable(R.drawable.ico)

        Log.e("aa","----" + BuildConfig.HTTP_API + "*******" + BuildConfig.isDebug);
    }*/

    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun initData() {

    }

    override fun initView() {
        setToolbarTitle("中国")

    }

    override fun initListener() {
        btn1.setOnClickListener {
            startActivity(Intent(mContext,TwoActivity::class.java))

        }

        btn2.setOnClickListener {

        }


    }

}
