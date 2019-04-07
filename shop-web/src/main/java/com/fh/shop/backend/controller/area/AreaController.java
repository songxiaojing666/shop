package com.fh.shop.backend.controller.area;

import com.fh.shop.backend.biz.area.IAreaService;
import com.fh.shop.backend.common.ServerResponse;
import com.fh.shop.backend.po.area.Area;
import com.fh.shop.backend.po.dept.Dept;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/area")

public class AreaController {

    @Resource(name = "areaService")
    private IAreaService areaService;


    //查询
    @RequestMapping("findAreaList")
    @ResponseBody//可以加在类上,方法上,方法public前面,后面
    public ServerResponse findAreaList(){
        List<Area> findlist=areaService.findAreaList();
        return ServerResponse.success(findlist);
    }
    //跳转到查询页面
    @RequestMapping("toAreaList")
    public String toAreaList(){
        return "area/areaList";
    }

    //新增
    @RequestMapping("showAddArea")
    @ResponseBody
    public ServerResponse showAddArea(Area area){
        areaService.showAddArea(area);
        //响应给前台一个id
        return ServerResponse.success(area.getId());
    }

    //修改
    @RequestMapping("editAreaDlg")
    @ResponseBody
    public ServerResponse editAreaDlg(Area area){
        areaService.editAreaDlg(area);
        return ServerResponse.success();

    }

    //删除部门
    @RequestMapping("deleteArea")
    @ResponseBody
    //"ids[]"和前台的key值对应,[]是前台的数据类型
    public ServerResponse deleteArea(@RequestParam("ids[]") List<Integer> ids){
        areaService.deleteDept(ids);
        return ServerResponse.success();
    }




}
