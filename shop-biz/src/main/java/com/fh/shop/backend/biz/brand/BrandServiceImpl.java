/** 
 * <pre>项目名称:shop-admin3 
 * 文件名称:BrandServiceImpl.java 
 * 包名:com.fh.shop.backend.biz.brand 
 * 创建日期:2018年12月28日下午6:40:44 
 * Copyright (c) 2018, 1071980754@qq.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.biz.brand;

import java.lang.reflect.Type;
import java.util.*;

import com.fh.shop.backend.biz.BaseService;
import com.fh.shop.backend.common.DataTableResult;
import com.fh.shop.backend.util.DateUtil;
import com.fh.shop.backend.util.RedisUtils;
import com.fh.shop.backend.util.SystemConstants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fh.shop.backend.mapper.brand.IBrandMapper;
import com.fh.shop.backend.po.brand.Brand;

/** 
 * <pre>项目名称：shop-admin3    
 * 类名称：BrandServiceImpl    
 * 类描述：     service的接口的实现层  
 * 创建人：宋晓静     2208459187  
 * 创建时间：2018年12月28日 下午6:40:44    
 * 修改人：宋晓静    2208459187  
 * 修改时间：2018年12月28日 下午6:40:44    
 * 修改备注：       
 * @version </pre>    
 */
@Service("brand")
public class BrandServiceImpl extends BaseService implements IBrandService{
	@Autowired
	private IBrandMapper iBrandMapper;

	//构建DataTables所需的数据
	@Override
	public DataTableResult buildBrandDataTable(Brand brand, Integer start, Integer length, Integer draw, Map<String, String[]> parameterMap) {
        //构建排序信息
		buildorder(parameterMap,brand);
		//给当前页下标赋值
		brand.setStartPos(start);
		//给每页条数赋值
		brand.setPageSize(length);
		//查询总条数
		Long longlist=findsearch(brand);
		//查询
		List<Brand> listbrand=querylistbrand(brand);
		//转换时间类型
		convertBrandList(listbrand);
		//组装结果
		return DataTableResult.build(draw, longlist, longlist, listbrand);
	}

	//构建排序信息
	private void buildorder( Map<String, String[]> parameterMap,Brand brand){
		//通过用Gson来使用硬编码操作
		Gson gson = new Gson();
		Map fieldMapping = gson.fromJson(SystemConstants.ORDER_DESC, Map.class);
		//排序的字段
		String orderField="";
		//排序的方向
		String orderDirection ="";
		//没有点击这获得的列是空的就不进判断中
		if (parameterMap.get(SystemConstants.ORDER_COLUMN)!=null){
			//提取排序的字段
			//1.通过获取点击的按钮在第几列
			String orderColumn = parameterMap.get(SystemConstants.ORDER_COLUMN)[0];
			//从而获取从前台传的名称
			String orderFieldTemp= parameterMap.get(getColumnData(orderColumn))[0];
			//做过映射后的字段名
			//第一种String orderInfo = (String) SystemConstants.ORDER_MAP.get(orderFieldTemp);
			String orderInfo = (String) fieldMapping.get(orderFieldTemp);//第二种
			//2.用三目运算符判断【为空时证明前台和数据库中的字段名一样,
			// 不为空时证明前台和数据库字段不一样得做映射】
			orderField= StringUtils.isEmpty(orderInfo)?orderFieldTemp:orderInfo;
		}
		//不为空时才走判断
		if (parameterMap.get(SystemConstants.DIR)!=null){
			//提取排序的方向【是升序还是降序】
			orderDirection = parameterMap.get(SystemConstants.DIR)[0];
			System.out.println(orderField+":"+orderDirection);

		}
		//给指定的排序赋值
		brand.setSortfield(orderField);
		brand.setSort(orderDirection);
		Map<String,Object> map = new HashMap<String,Object>();
	}

	//后台进行将时间转换为String字符串响应到前台
	private void convertBrandList(List<Brand> listbrand) {
		for (Brand brand1 : listbrand) {
			brand1.setInsertDateTime(DateUtil.date2str(brand1.getInsertDate(),DateUtil.FULL_DATE));
			brand1.setUpdateDateTime(DateUtil.date2str(brand1.getUpdateDate(),DateUtil.FULL_DATE));
		}
	}



	/* 查询总条数
	 */
	@Override
	public Long findsearch(Brand brand) {
		return iBrandMapper.findsearch(brand);
	}

	/* 查询
	 */
	@Override
	public List<Brand> querylistbrand(Brand brand) {
		return iBrandMapper.querylistbrand(brand);
	}

	/* 新增保存到数据库
	 */
	@Override
	public void addlistbrand(Brand brand) {
		Date date=new Date();
		brand.setInsertDate(date);
		brand.setUpdateDate(date);
		iBrandMapper.addlistbrand(brand);
		//throw new RuntimeException("ggggg");手动抛异常
	}

	/* 删除
	 */
	@Override
	public void deletelistbrand(Integer id) {
		iBrandMapper.deletelistbrand(id);
	}

	/* 批量删除   
	 */
	@Override
	public void deletemore(String ids) {
		List<Integer> list=new ArrayList<Integer>();
		String[] split = ids.split(",");
		for (String id : split) {
			list.add(Integer.parseInt(id));
	}
		iBrandMapper.deletemore(list);
}

	/* 回显
	 */
	@Override
	public Brand brandtoupdate(Integer id) {
		return iBrandMapper.brandtoupdate(id);
	}

	/* 修改
	 */
	@Override
	public void updatelistbrand(Brand brand) {
		Date date=new Date();
		brand.setUpdateDate(date);
		iBrandMapper.updatelistbrand(brand);
	}

	/* 普通的品牌查询做动态下拉框
	 */
	@Override
	public List<Brand> listbrand() {
		String brandListInfo = RedisUtils.get("brandList");
		Gson gson = new Gson();
		if (StringUtils.isEmpty(brandListInfo)){
		List<Brand> brandList = iBrandMapper.listbrand();
			String brandListJson = gson.toJson(brandList);
			RedisUtils.set("brandList", brandListJson);
			return brandList;
		}else {
			Type type = new TypeToken<List<Brand>>() {

			}.getType();
			List<Brand> brandList = gson.fromJson(brandListInfo, type);
			return brandList;


		}
	}
}
