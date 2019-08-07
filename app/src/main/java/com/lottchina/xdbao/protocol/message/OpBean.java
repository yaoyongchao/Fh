package com.lottchina.xdbao.protocol.message;

import java.util.List;

/**
 * Author: Austin
 * Date: 19-5-13
 * Description:
 */
public class OpBean {

    /**
     * limit : 10
     * offset : 0
     * sort : [{"end_time":1},{"amount":-1}]
     */

    private int limit;
    private int offset;
    private List<SortBean> sort;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public List<SortBean> getSort() {
        return sort;
    }

    public void setSort(List<SortBean> sort) {
        this.sort = sort;
    }


}
