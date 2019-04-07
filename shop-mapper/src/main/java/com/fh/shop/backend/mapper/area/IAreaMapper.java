package com.fh.shop.backend.mapper.area;

import com.fh.shop.backend.po.area.Area;

import java.util.List;

public interface IAreaMapper {
    //查询
    List<Area> findAreaList();
    //新增
    void showAddArea(Area area);
    //修改
    void editAreaDlg(Area area);
    //删除部门
    void deleteDept(List<Integer> ids);
}
