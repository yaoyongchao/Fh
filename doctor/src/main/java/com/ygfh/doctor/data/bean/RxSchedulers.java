package com.ygfh.doctor.data.bean;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import com.fh.baselib.utils.L;
import com.fh.baselib.utils.NetWorkUtils;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author: Page
 * @time: 17-8-4
 * @description: The thread scheduling and request preprocessing tools.
 */

public class RxSchedulers {
    private static ProgressDialog dialog;

    /**
     * @param: [context]
     * @return: io.reactivex.ObservableTransformer<T,T>
     * @description: Thread Scheduling. The default is show dialog.
     */
    public static <T> ObservableTransformer<T, T> ioMain(final Context context) {
        return ioMain(context,true);
    }

    /**
     * @param: [context, isShow] [context，Whether or not show dialog.]
     * @return: io.reactivex.ObservableTransformer<T,T>
     * @description: Thread Scheduling.
     */
    public static <T> ObservableTransformer<T, T> ioMain(final Context context, final boolean isShow) {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> observable) {
                if (isShow && dialog == null) {
                    dialog = new ProgressDialog(context);
                    dialog.setCancelable(true);
                    dialog.setMessage("加载中...");
                }
                return observable
                        .subscribeOn(Schedulers.io())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(final Disposable disposable) throws Exception {
                                L.Companion.d("doOnSubscribe -- Determine if there is a network and dialog.");

                                if (!NetWorkUtils.Companion.isConnectedByState(context)) {
                                    disposable.dispose();
                                    L.Companion.d("doOnSubscribe -- There's no network link.");
                                    Toast.makeText(context, "没有网络", Toast.LENGTH_SHORT).show();
                                } else {
                                    L.Companion.d("doOnSubscribe -- There's a network link.");
                                    if (isShow && dialog != null && !dialog.isShowing()) {
                                        dialog.show();
                                        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                                            @Override
                                            public void onCancel(DialogInterface dialog) {
                                                L.Companion.i("Dialog Cancel and interrupt network request!");
                                                disposable.dispose();
                                            }
                                        });
                                    }

                                }
                            }
                        })
                        .doFinally(new Action() {
                            @Override
                            public void run() throws Exception {
                                if (dialog != null && dialog.isShowing()){
                                    dialog.dismiss();
                                    dialog = null;
                                }
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


}
