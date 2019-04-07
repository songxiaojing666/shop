package com.fh.shop.backend.biz.dept;

import com.fh.shop.backend.po.dept.Dept;

import java.util.List;

public interface IDeptService {
    //查询
    public List<Dept> findDeptList();
    //新增
    public void addDept(Dept dept);

    //修改
    public void editDeptDlg(Dept dept);
    //删除部门
    public void deleteDept(List<Integer> ids);
    //查询有几个sheet页并且取出来名称
    List<Dept> deptExcelList(List<Integer> childNode);
    //根据父节点查看所有的子节点
    List<Integer>  findDeptByFatherId(Integer id);
    //清空集合中的数据
    void cleanList();
}
