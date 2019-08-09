package com.fh.cplib.data.base

//import com.fh.cplib.bean.base.RequestHead


/**
 * Author: Austin
 * Time: 2018/7/23
 * Description:
 */
class BaseRequestBean {
    var head: RequestHead? = null
    var body: String? = null

    constructor(messageHead: RequestHead, body: String) {
        this.head = messageHead
        this.body = body
    }

    constructor() {}
}
