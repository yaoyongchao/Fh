package com.ygfh.doctor.data.bean;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RxjavaHelper {

/**
 * 切换线程操作
 * @return Observable转换器
 */
public static <T> ObservableTransformer<T, T> observeOnMainThread() {
    return new ObservableTransformer<T, T>() {

        @Override
        public ObservableSource<T> apply(Observable<T> upstream) {
            return upstream.subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    };
}


}