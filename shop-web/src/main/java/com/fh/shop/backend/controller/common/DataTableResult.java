package com.fh.shop.backend.controller.common;

import java.io.Serializable;
import java.util.List;

public class DataTableResult implements Serializable {
    private static final long serialVersionUID = 5710999222800174410L;
    private int draw;
    private long recordsTotal;
    private long recordsFiltered;
    private List data;

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public long getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    private DataTableResult(int draw, long recordsTotal, long recordsFiltered,List data) {
        super();
        this.draw = draw;
        this.recordsTotal = recordsTotal;
        this.recordsFiltered = recordsFiltered;
        this.data=data;
    }


    public static DataTableResult build(int draw, long recordsTotal, long recordsFiltered,List data){
        //通过有参构造方法赋值给前台响应值
        return new DataTableResult(draw,recordsTotal,recordsFiltered,data);
    }


}
