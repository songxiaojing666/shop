package com.fh.shop.backend.controller.dept;

import com.fh.shop.backend.biz.dept.IDeptService;
import com.fh.shop.backend.common.ServerResponse;
import com.fh.shop.backend.po.dept.Dept;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/dept/")
@ResponseBody//可以加在类上,方法上,方法public前面,后面
public class DeptController {
    @Resource(name = "deptservice")
    private IDeptService eptService;
    //查询
    @RequestMapping("findDeptList")
    public ServerResponse findDeptList(){
        List<Dept> findlist=eptService.findDeptList();
       return ServerResponse.success(findlist);
    }
    //新增
    @RequestMapping("addDept")
    public ServerResponse addDept(Dept dept){
        eptService.addDept(dept);
        System.out.println(dept.getId());
        //响应给前台一个id
        return ServerResponse.success(dept.getId());
    }
    //删除部门
    @RequestMapping("deleteDept")
    //"ids[]"和前台的key值对应,[]是前台的数据类型
    public ServerResponse deleteDept(@RequestParam("ids[]") List<Integer> ids){
        eptService.deleteDept(ids);
        return ServerResponse.success();
    }

    //修改
    @RequestMapping("editDeptDlg")
    public ServerResponse editDeptDlg(Dept dept){
        eptService.editDeptDlg(dept);
        return ServerResponse.success();

    }


}
