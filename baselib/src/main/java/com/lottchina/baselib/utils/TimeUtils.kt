package com.vcaidian.customer.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * Author: Austin
 * Time: 2018/7/23
 * Description: 时间工具类
 */
object TimeUtils {

    var simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS")

    val timeStamp: String
        get() = getTimeStamp(Date())

    fun getTimeStamp(date: Date): String {
        return simpleDateFormat.format(date)
    }
}
