package com.vcaidian.customer.bean.base

class RequestHead {

    var cmd: String? = null

    var digest: String? = null

    var digestType: String? = null

    var timeStamp: String? = null

    var lowerTimestamp: String? = null

    var userId: String? = null

    var userType: String? = null

    override fun toString(): String {
        return ("MessageHead [cmd=" + cmd + ", digest=" + digest + ", digestType=" + digestType + ", timeStamp="
                + timeStamp + ", userId=" + userId + ", userType=" + userType + "]")
    }


}
