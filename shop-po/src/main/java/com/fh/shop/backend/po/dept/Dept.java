package com.fh.shop.backend.po.dept;

import com.fh.shop.backend.po.Page;

import java.io.Serializable;

public class Dept extends Page implements Serializable {
    //序列号
    private static final long serialVersionUID = -3776430122152672460L;
    private Integer id;//id
    private Integer fatherid;//pid
    private String deptName;//name
    private String remark;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFatherid() {
        return fatherid;
    }

    public void setFatherid(Integer fatherid) {
        this.fatherid = fatherid;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
