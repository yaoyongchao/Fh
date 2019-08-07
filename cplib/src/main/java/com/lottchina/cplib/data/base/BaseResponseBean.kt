package com.vcaidian.wclib.wc

/**
 * Author: Austin
 * Time: 2018/7/23
 * Description:
 */
public class BaseResponseBean {
    var head: ResponseHead? = null
    var body: String? = null

    constructor(messageHead: ResponseHead, body: String) {
        this.head = messageHead
        this.body = body
    }

    constructor() {}
}
