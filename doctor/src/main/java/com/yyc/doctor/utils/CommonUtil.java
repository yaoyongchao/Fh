package com.yyc.doctor.utils;

import android.content.Context;

import com.vcaidian.customer.common.CommonParam;
import com.yyc.doctor.DcApplication;

/**
 * Author: YongChao
 * Date: 19-8-7 下午4:12
 * Description:
 */
public class CommonUtil {
    public static Context getContext() {
        return DcApplication.appContext;
    }
    public static void saveFirstUse() {
        SPObjUtil.putInt(getContext(), CommonParam.INSTANCE.getIS_FIRST(),1);
    }

    /**
     * 0 表示第一次使用， 1表示不是第一次使用
     * @return
     */
    public static boolean isFirstUse() {
        return SPObjUtil.getInt(getContext(),CommonParam.INSTANCE.getIS_FIRST(),0) == 0 ? true : false;
    }

}
