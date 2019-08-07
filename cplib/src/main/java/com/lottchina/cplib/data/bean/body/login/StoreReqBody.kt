package com.lottchina.cplib.data.bean.body.login

import com.lottchina.cplib.data.base.BaseRequestBody

/**
 * Author: Austin
 * Date: 19-5-6
 * Description:
 */
class StoreReqBody : BaseRequestBody() {

    /**
     * account : 1
     * channel : 0
     * customer : 1
     * station : 1
     * terminal : 1
     */

    var account: Int = 0
    var channel: Int = 0
    var customer: Int = 0
    var station: Int = 0
    var terminal: Int = 0
}
