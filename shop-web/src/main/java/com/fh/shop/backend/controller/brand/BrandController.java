/** 
 * <pre>项目名称:shop-admin3 
 * 文件名称:BrandController.java 
 * 包名:com.fh.shop.backend.controller.brand 
 * 创建日期:2018年12月28日下午6:42:33 
 * Copyright (c) 2018, 1071980754@qq.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.controller.brand;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fh.shop.backend.common.DataTableResult;
import com.fh.shop.backend.common.ServerResponse;
import com.fh.shop.backend.util.DateUtil;
import com.fh.shop.backend.util.SystemConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fh.shop.backend.biz.brand.IBrandService;
import com.fh.shop.backend.controller.BaseController;
import com.fh.shop.backend.po.brand.Brand;

/** 
 * <pre>项目名称：shop-admin3    
 * 类名称：BrandController    
 * 类描述：控制层    
 * 创建人：宋晓静     2208459187  
 * 创建时间：2018年12月28日 下午6:42:33    
 * 修改人：宋晓静    2208459187  
 * 修改时间：2018年12月28日 下午6:42:33    
 * 修改备注：       
 * @version </pre>    
 */
@Controller//声明控制层
@RequestMapping("/brand/")
public class BrandController extends BaseController{
	  @Resource(name="brand")
	  private IBrandService  iBrandService;
	  
	  /**
	   * <pre>findsearch(查询和分页条件查询)   
	   * 创建人：宋晓静       2208459187@qq.com    
	   * 创建时间：2018年12月28日 下午8:09:41    
	   * 修改人：宋晓静       2208459187@qq.com     
	   * 修改时间：2018年12月28日 下午8:09:41    
	   * 修改备注： 
	   * @return</pre>
	   */
	  @RequestMapping("querylistbrand")
	  @ResponseBody
	 public ServerResponse querylistbrand(Brand brand, Integer flag, Integer start, Integer length, Integer draw, HttpServletRequest request){
		  //通过HttpServletRequest获取前台传的参数,取得里面的所有键值对
		  //获取所有请求的参数
		  Map<String, String[]> parameterMap = request.getParameterMap();
		  //构建DataTables所需的数据
		  DataTableResult result = iBrandService.buildBrandDataTable(brand, start, length, draw, parameterMap);
		  return ServerResponse.success(result);
	 }

	//跳转到展示页面
	@RequestMapping("toBandList")
	public String toBandList(){
	  	return "/brand/brandList";
	}
	  /**
	   * <pre>toaddlistbrand(新增)   
	   * 创建人：宋晓静       2208459187@qq.com    
	   * 创建时间：2018年12月28日 下午8:58:34    
	   * 修改人：宋晓静       2208459187@qq.com     
	   * 修改时间：2018年12月28日 下午8:58:34    
	   * 修改备注： 
	   * @return</pre>
	   */
	@RequestMapping("toaddlistbrand")
	public String toaddlistbrand(){
		return "brand/add";
	}
	/**
	 * <pre>addlistbrand(新增保存到数据库)   
	 * 创建人：宋晓静       2208459187@qq.com    
	 * 创建时间：2018年12月28日 下午9:03:13    
	 * 修改人：宋晓静       2208459187@qq.com     
	 * 修改时间：2018年12月28日 下午9:03:13    
	 * 修改备注： 
	 * @param brand
	 * @return</pre>
	 */
	@RequestMapping("addlistbrand")
	public String addlistbrand(Brand brand){
			iBrandService.addlistbrand(brand);
		    return "redirect:toBandList.jhtml";
	}  
	/**
	 * <pre>deletelistbrand(删除)   
	 * 创建人：宋晓静       2208459187@qq.com    
	 * 创建时间：2018年12月28日 下午9:22:50    
	 * 修改人：宋晓静       2208459187@qq.com     
	 * 修改时间：2018年12月28日 下午9:22:50    
	 * 修改备注： 
	 * @param id
	 * @param
	 */
	
	@RequestMapping("deletelistbrand")
	@ResponseBody
	public ServerResponse deletelistbrand(Integer id){
		iBrandService.deletelistbrand(id);
		return ServerResponse.success();

	}
	/**
	 * <pre>deletemore(批量删除)   
	 * 创建人：宋晓静       2208459187@qq.com    
	 * 创建时间：2018年12月28日 下午9:37:27    
	 * 修改人：宋晓静       2208459187@qq.com     
	 * 修改时间：2018年12月28日 下午9:37:27    
	 * 修改备注： 
	 * @param ids</pre>
	 */
	@RequestMapping("deletemore")
	@ResponseBody
	public ServerResponse deletemore(String ids,HttpServletResponse response){
		iBrandService.deletemore(ids);
		return ServerResponse.success();
	}
	
	/**
	 * <pre>brandtoupdate(回显)   
	 * 创建人：宋晓静       2208459187@qq.com    
	 * 创建时间：2018年12月28日 下午10:02:37    
	 * 修改人：宋晓静       2208459187@qq.com     
	 * 修改时间：2018年12月28日 下午10:02:37    
	 * 修改备注： 
	 * @return</pre>
	 */
	@RequestMapping("brandupdateto")
	public ModelAndView brandupdateto(Integer id){
		ModelAndView mav=new ModelAndView("brand/update");
		Brand brand=iBrandService.brandtoupdate(id);
		mav.addObject("brandinfo",brand);
		return mav;
	}
	/**
	 * <pre>updatelistbrand(修改)   
	 * 创建人：宋晓静       2208459187@qq.com    
	 * 创建时间：2018年12月28日 下午10:23:12    
	 * 修改人：宋晓静       2208459187@qq.com     
	 * 修改时间：2018年12月28日 下午10:23:12    
	 * 修改备注： 
	 * @param brand
	 * @return</pre>
	 */
	@RequestMapping("updatelistbrand")
	public String updatelistbrand(Brand brand){
			iBrandService.updatelistbrand(brand);
		return "redirect:toBandList.jhtml";
	}

	
	/**
	 * <pre>list(动态下拉框的品牌表)
	 * 创建人：宋晓静       2208459187@qq.com    
	 * 创建时间：2019年1月10日 下午8:28:34    
	 * 修改人：宋晓静       2208459187@qq.com     
	 * 修改时间：2019年1月10日 下午8:28:34    
	 * 修改备注： 
	 * @return</pre>
	 * 1. public  @ResponseBody Map list(){}
	 * 2. @ResponseBody
	      public Map list(){}
	   3.@ResponseBody public Map list(){}
	 */
	 @RequestMapping("list")
	 public @ResponseBody ServerResponse list(){
		List<Brand> listbrand=iBrandService.listbrand();
		return ServerResponse.success(listbrand);
	 }
	  

}
