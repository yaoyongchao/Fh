package com.ygfh.doctor.ui.demo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.fh.baselib.http.BaseObserver;
import com.fh.baselib.utils.L;
import com.ygfh.doctor.R;
import com.ygfh.doctor.data.bean.Login;
import com.ygfh.doctor.data.bean.RxSchedulers;
import com.ygfh.doctor.net.DcServiceFactory;

import org.jetbrains.annotations.Nullable;

public class TwoActivity extends AppCompatActivity {
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        context = this;
        initViews();
    }

    private void initViews() {
        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DcServiceFactory.INSTANCE.getService()
                        .login2()
                        .compose(RxSchedulers.ioMain(context))
                        .subscribe(new BaseObserver<Login>() {
                            @Override
                            public void onSuccess(@Nullable Login login) {
                                L.Companion.e("login:" + login);
                            }
                        });
            }
        });
    }
}
