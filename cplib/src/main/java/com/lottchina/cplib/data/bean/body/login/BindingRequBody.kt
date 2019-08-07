package com.lottchina.cplib.data.bean.body.login

import com.lottchina.cplib.data.base.BaseRequestBody

/**
 * Author: Austin
 * Date: 19-4-1
 * Description:绑定店铺请求体
 */

class BindingRequBody(val show_terminal: Boolean = true,
                      var username: String = ""): BaseRequestBody() {



}