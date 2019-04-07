package com.fh.shop.backend.po.area;

import java.io.Serializable;

public class Area implements Serializable {
    private static final long serialVersionUID = -5441364763295128171L;
    private Integer id;//id
    private Integer pid;//pid
    private String name;//name

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
