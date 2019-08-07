package com.lottchina.xdbao.net

import com.vcaidian.baselib.http.ServiceFactory

/**
 * Author: Austin
 * Date: 19-4-11
 * Description:
 */

object CpServiceFactory {
    fun getService(): CpApiService {
        return ServiceFactory.getService(CpApi.CP_RUL,CpApiService::class.java)
    }
}