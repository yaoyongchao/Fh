package com.lottchina.cplib.data.bean.body.login

import com.lottchina.cplib.data.base.BaseRequestBody

/**
 * Author: Austin
 * Date: 19-5-5
 * Description:
 */
class LoginReqBody : BaseRequestBody() {

    /**
     * code : 1346497643215
     * login_pass : 111111
     * station_id : 4
     */

    var code: String? = null
    var login_pass: String? = null
    var station_id: Int = 0
}
