package com.fh.shop.backend.mapper.dept;

import com.fh.shop.backend.po.dept.Dept;

import java.util.List;

public interface IDeptMapper {
    //查询
    public List<Dept> findDeptList();
    //新增
    public void addDept(Dept dept);
    //如果删除的id是父节点的话 它的子节点的父节点都是当前id
    //所以根据父节点查看
    public List<Dept> findDeptById(Integer id);
    //修改
    public void editDeptDlg(Dept dept);
    //删除部门
    public void deleteDept(List<Integer> ids);
    //查询有几个sheet页并且取出来名称
    List<Dept> deptExcelList(List<Integer> childNode);
    //根据父节点查看所有的子节点
    List<Dept> findDeptByFatherId(Integer id);
}
