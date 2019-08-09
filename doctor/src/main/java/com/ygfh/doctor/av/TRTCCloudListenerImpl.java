package com.ygfh.doctor.av;

import android.os.Bundle;
import android.util.Log;

import com.tencent.trtc.TRTCCloudListener;
import com.ygfh.doctor.ui.login.LoginActivity;

import java.lang.ref.WeakReference;

/**
 * Author: YongChao
 * Date: 19-8-8 下午5:17
 * Description:
 */
public class TRTCCloudListenerImpl extends TRTCCloudListener {
    private static final String TAG = "TRTCCloudListenerImpl";
    private WeakReference<LoginActivity> mContext;
    public TRTCCloudListenerImpl(LoginActivity activity) {
        super();
        mContext = new WeakReference<>(activity);
    }

    @Override
    public void onUserEnter(String s) {
        super.onUserEnter(s);
        log("userEnter");
    }

    @Override
    public void onUserExit(String s, int i) {
        super.onUserExit(s, i);
        log("UserExit");
    }


    @Override
    public void onError(int i, String s, Bundle bundle) {
        super.onError(i, s, bundle);
        log("onError");
    }

    private void log(String str) {
        Log.e(TAG,str);

    }
}
