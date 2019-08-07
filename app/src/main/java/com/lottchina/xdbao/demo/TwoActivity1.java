package com.lottchina.xdbao.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lottchina.xdbao.R;

import java.util.ArrayList;
import java.util.Arrays;

public class TwoActivity1 extends AppCompatActivity {
    private ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        list = (ArrayList<String>) Arrays.asList(getResources().getStringArray(R.array.lottery_friend_sort));
    }
}
