package com.fh.shop.backend.biz;

import java.io.Serializable;

public class BaseService implements Serializable {
    private static final long serialVersionUID = -1171438551981735386L;
    //按钮排序时获取得字段名【protected受保护的】
    protected String getColumnData(String orderColumn) {
        return "columns["+ orderColumn + "][data]";
    }



}
