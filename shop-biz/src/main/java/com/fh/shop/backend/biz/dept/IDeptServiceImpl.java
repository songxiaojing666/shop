package com.fh.shop.backend.biz.dept;

import com.fh.shop.backend.mapper.dept.IDeptMapper;
import com.fh.shop.backend.po.dept.Dept;
import com.fh.shop.backend.util.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("deptservice")
public class IDeptServiceImpl implements IDeptService {
    @Autowired
    private IDeptMapper deptMapper;

    //查询
    @Override
    public List<Dept> findDeptList() {
        //缓存
        CacheManager cacheManager = CacheManager.getInstance();
        Object deptlist = cacheManager.getObj("deptlist");
        //判断缓存中是否有值,没值就到数据库中查询
        if (deptlist!=null){
            return (List<Dept>) deptlist;
        }
        List<Dept> deptList = deptMapper.findDeptList();
        //将查到的数据放到缓存中
        cacheManager.putObj("deptlist",deptList);
        return deptList;
    }

    //新增
    @Override
    public void addDept(Dept dept) {
        deptMapper.addDept(dept);
        //手动清除缓存
        CacheManager.getInstance().remove("deptlist");
    }

    //修改
    @Override
    public void editDeptDlg(Dept dept) {
        deptMapper.editDeptDlg(dept);
        //手动清除缓存
        CacheManager.getInstance().remove("deptlist");
    }

    //删除部门
    @Override
    public void deleteDept(List<Integer> ids) {
        deptMapper.deleteDept(ids);
        //手动清除缓存
        CacheManager.getInstance().remove("deptlist");
    }

    //查询有几个sheet页并且取出来名称
    @Override
    public List<Dept> deptExcelList(List<Integer> childNode) {
        return deptMapper.deptExcelList(childNode);
    }

    private List<Integer> list = new ArrayList<Integer>();
    //根据父节点查看所有的子节点
    @Override
    public List<Integer> findDeptByFatherId(Integer id) {
        List<Dept> isDept= deptMapper.findDeptByFatherId(id);
        list.add(id);
        //不为空时
        if (!isDept.isEmpty()){
            //循环
            for (int i = 0; i < isDept.size(); i++) {
                Dept dept = isDept.get(i);
                list.add(dept.getId());
                //利用递归来寻找每个父节点下的子节点
                this.findDeptByFatherId(dept.getId());
            }
        }
        return list;

    }
    //清空集合中的数据
    @Override
    public void cleanList() {
       list = new ArrayList<Integer>();
    }

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }
}
