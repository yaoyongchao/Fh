package com.vcaidian.wclib.mvp

import com.lottchina.baselib.mvp.BaseView
import com.vcaidian.baselib.mvp.BaseModel
import com.vcaidian.baselib.utils.CreatUtil
import java.lang.ref.WeakReference

abstract class BasePresenter<V: BaseView, M: BaseModel> {
    var view: WeakReference<V>? = null
    var mModel: M? = null
    init {
        mModel = CreatUtil.getT(this,0)
//        L.e("mModel: $mModel")
    }

    fun bindView(view: V) {
        this.view = WeakReference(view)
//        L.e("view: $view" )
    }

    fun isBind(): Boolean {
        return view != null && view!!.get() != null
    }

    fun unBindView() {
        if (isBind()) {
            view!!.clear()
            view = null
        }
    }

    fun obtainView(): V? {
        return if (isBind()) view!!.get() else null
    }

}