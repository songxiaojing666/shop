package com.fh.shop.backend.biz.area;

import com.fh.shop.backend.mapper.area.IAreaMapper;
import com.fh.shop.backend.mapper.dept.IDeptMapper;
import com.fh.shop.backend.po.area.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("areaService")
public class IAreaServiceImpl implements IAreaService {
    @Autowired
    private IAreaMapper areaMapper;
    //查询
    @Override
    public List<Area> findAreaList() {
        return areaMapper.findAreaList();
    }
    //新增
    @Override
    public void showAddArea(Area area) {
        areaMapper.showAddArea(area);
    }
    //修改
    @Override
    public void editAreaDlg(Area area) {
        areaMapper.editAreaDlg(area);
    }
    //删除部门
    @Override
    public void deleteDept(List<Integer> ids) {
        areaMapper.deleteDept(ids);
    }
}
