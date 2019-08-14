package com.fh.baselib.http


import com.fh.baselib.BaseApplication
import com.fh.baselib.http.entity.BaseEntity
import com.fh.baselib.utils.L
import com.fh.baselib.utils.NetWorkUtils
import com.fh.baselib.utils.ToastUtil
import io.reactivex.Observer
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.Disposable

/**
 * Author: Austin
 * Date: 2018/10/9
 * Description:
 */
//abstract class BaseObserver<T> (context: Context) : Observer<BaseEntity<T>> {
abstract class BaseObserver<T> : Observer<BaseEntity<T>> {
//    private val mContext: Context
    val  TAG = "BaseObserver"
    private val SUCCESS_CODE = 200

    override fun onSubscribe(@NonNull d: Disposable) {

    }

    override fun onNext(@NonNull tBaseEntity: BaseEntity<T>) {
//        L.i("onNext")
        if (SUCCESS_CODE == tBaseEntity.code) {
            val t = tBaseEntity.data
            onSuccess(t)
        } else {
            L.i("onNext--Failure--code:" + tBaseEntity.code + "--Message:" + tBaseEntity.msg)
            onFail(tBaseEntity.msg)
        }

    }

    override fun onError(@NonNull e: Throwable) {
        L.e("TAG--onError--" + e.toString())
        if (!NetWorkUtils.Companion.isConnectedByState(BaseApplication.appContext)) {
//            ToastUtil.show("网络异常请检查网络")
            onFail("网络异常请检查网络")
        }
//        ExceptionHandle.handleException(e)
    }

    override fun onComplete() {
        L.d("TAG--onComplete")

    }

    abstract fun onSuccess(t: T?)

    open fun onFail(msg: String) {
        ToastUtil.show(msg)
    }
}
