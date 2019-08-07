package com.lottchina.cplib.data.bean.body.login

import com.lottchina.cplib.data.bean.Terminal
import com.vcaidian.wclib.wc.BaseResponseBody

/**
 * Author: Austin
 * Date: 19-5-6
 * Description:
 */
class LoginResBody : BaseResponseBody() {
    /**
     * err : {"code":0,"des":"操作成功"}
     * token : fa5b1dd6c3e3fa4f2a7a4181728dc9ef
     * user_id : 4
     */
    var token: String? = null
    var user_id: Int = 0
    var terminal: Terminal? = null

}
