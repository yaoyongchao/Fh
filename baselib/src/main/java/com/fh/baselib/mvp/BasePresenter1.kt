package com.fh.baselib.mvp

import com.fh.baselib.utils.CreatUtil
import java.lang.ref.WeakReference

abstract class BasePresenter1<V: BaseView, M: BaseModel> {
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