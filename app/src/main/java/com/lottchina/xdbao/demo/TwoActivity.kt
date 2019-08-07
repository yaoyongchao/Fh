package com.lottchina.xdbao.demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import com.lottchina.xdbao.R

import java.util.ArrayList
import java.util.Arrays

class TwoActivity : AppCompatActivity() {
    private var list: ArrayList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two)
        list = Arrays.asList(*resources.getStringArray(R.array.lottery_friend_sort)) as ArrayList<String>
    }
}
