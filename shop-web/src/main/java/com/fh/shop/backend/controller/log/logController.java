/**
 * <pre>项目名称:shop_admin_m1
 * 文件名称:logcontroller.java
 * 包名:com.fh.shop.backend.controller.log
 * 创建日期:2019年1月10日下午10:20:11
 * Copyright (c) 2019, 1071980754@qq.com All Rights Reserved.</pre>
 */
package com.fh.shop.backend.controller.log;

import javax.annotation.Resource;

import com.fh.shop.backend.po.log.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fh.shop.backend.biz.log.ILogService;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>项目名称：shop_admin_m1
 * 类名称：logcontroller
 * 类描述：
 * 创建人：宋晓静     2208459187
 * 创建时间：2019年1月10日 下午10:20:11
 * 修改人：宋晓静    2208459187
 * 修改时间：2019年1月10日 下午10:20:11
 * 修改备注：
 * @version </pre>
 */
@Controller
@RequestMapping("/log/")
public class logController {
    //注入到service层
    @Resource(name="logService")
    private ILogService logService;

    /**
     * 查询
     */
    @RequestMapping("findlog")
    @ResponseBody
    public Map findlog(Log log, Integer start,Integer length,Integer draw, Integer flag) {
        Map<String,Object> map = new HashMap<String,Object>();
        //给当前页下标赋值
        log.setStartPos(start);
        //给每页条数赋值
        log.setPageSize(length);
        //获取分页的总条数
        Long pageCount = logService.listlog(log);
        //普通的条件查询和查询
        List<Log> listlog = logService.findlog(log);
        map.put("draw",draw);
        map.put("recordsFiltered",pageCount);
        map.put("recordsTotal",listlog);
        map.put("data",listlog);
        return map;
    }

    //跳转到展示页面
    @RequestMapping("toLog")
    public String toLog(){
        return "/log/logList";
    }







}
