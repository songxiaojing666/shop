/** 
 * <pre>项目名称:shop-admin3 
 * 文件名称:ProductController.java 
 * 包名:com.fh.shop.backend.controller.product 
 * 创建日期:2018年12月23日下午9:11:05 
 * Copyright (c) 2018, 1071980754@qq.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.controller.product;


import java.io.IOException;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fh.shop.backend.annotation.LogAnnotation;
import com.fh.shop.backend.common.DataTableResult;
import com.fh.shop.backend.common.FileInfo;
import com.fh.shop.backend.common.ServerResponse;
import com.fh.shop.backend.po.product.ProductImage;
import com.fh.shop.backend.util.DateUtils;
import com.fh.shop.backend.util.FileUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fh.shop.backend.biz.brand.IBrandService;
import com.fh.shop.backend.biz.product.IProductService;
import com.fh.shop.backend.controller.BaseController;
import com.fh.shop.backend.po.brand.Brand;
import com.fh.shop.backend.po.product.Product;

/** 
 * <pre>项目名称：shop-admin3    
 * 类名称：ProductController    
 * 类描述：    
 * 创建人：宋晓静     2208459187  
 * 创建时间：2018年12月23日 下午9:11:05    
 * 修改人：宋晓静    2208459187  
 * 修改时间：2018年12月23日 下午9:11:05    
 * 修改备注：       
 * @version </pre>    
 */
@Controller
public class ProductController extends BaseController{
	//产品表的service接口
	@Resource(name = "product")
	private IProductService iProductService;

	//品牌表的service接口
	@Autowired
	private IBrandService iBrandService;

	/**
	 * <pre>toAddProduct(跳转新增页面)   
	 * 创建人：宋晓静       2208459187@qq.com    
	 * 创建时间：2018年12月23日 下午9:21:50    
	 * 修改人：宋晓静       2208459187@qq.com     
	 * 修改时间：2018年12月23日 下午9:21:50    
	 * 修改备注： 
	 * @return</pre>
	 */
	@RequestMapping("/product/toadd")
	public String toAddProduct(ModelMap map){
		return "product/add";
	}


	/**
	 * <pre>addproduct(新增提交到数据库)   
	 * 创建人：宋晓静       2208459187@qq.com    
	 * 创建时间：2018年12月23日 下午9:22:33    
	 * 修改人：宋晓静       2208459187@qq.com     
	 * 修改时间：2018年12月23日 下午9:22:33    
	 * 修改备注： 
	 * @return</pre>
	 */
	@RequestMapping("/product/add")
	//自定义属性(使用户可以看到执行的信息)
	@LogAnnotation("添加产品")
	public String addproduct(Product product,
							 @RequestParam MultipartFile shopFile,
							 @RequestParam MultipartFile[] childsImages,
							 HttpServletRequest request) throws IOException {
		//获取项目在硬盘上的跟目录【提取一个公共方法getRootPach()在BaseController类中】
		String rootPach = getRootPach(request);
		//主图片转换后的文件信息【提取一个公共方法convertFileInfo()在BaseController类中】
		FileInfo fileInfo = convertFileInfo(shopFile);
		//提取一个方法【在BaseController类中获取前台传的多张图片】
		List<FileInfo> fileInfos= convertFileInfoList(childsImages);
		//添加信息
		iProductService.addproduct(product,fileInfo,rootPach,fileInfos);
		return "redirect:/product/toList.jhtml";
	}

	/**
	 * <pre>querylist(查询)   
	 * 创建人：宋晓静       2208459187@qq.com    
	 * 创建时间：2018年12月25日 下午11:52:12    
	 * 修改人：宋晓静       2208459187@qq.com     
	 * 修改时间：2018年12月25日 下午11:52:12    
	 * 修改备注： 
	 * @return</pre>
	 */
	@RequestMapping("/product/querylist")
	@ResponseBody
	public ServerResponse querylist(Product product, Integer start, Integer length, Integer draw, HttpServletRequest request){
		//获取所有请求的参数
		Map<String,Object> map = new HashMap<String,Object>();
		//根据定义DataTables所需的数据
		DataTableResult result = iProductService.buildProductDataTable(product, start, length, draw, request);
		return ServerResponse.success(result);
	}

	//跳转到查询页面
	@RequestMapping("/product/toList")
	public String toList(){
		return "product/product";
	}

	//查询子图片
	@RequestMapping("/product/queryproductImage")
	public  String queryproductImage(ModelMap result,Integer id){
		List<ProductImage> productImages=iProductService.queryproductImage(id);
		result.put("productImages", productImages);
		return "product/image";
	}

	/**java对象转换成json格式的字符串响应给客户端
	 * 通过 response获取writer
	 * 再通过writer将字符串响应给客户端(浏览器)
	 * (删除调用了一个工具类是自己手敲的
	 * 调用的是apach旗下的【json--lib】
	 * )
	 * 创建人：宋晓静       2208459187@qq.com    
	 * 创建时间：2018年12月26日 上午12:05:16    
	 * 修改人：宋晓静       2208459187@qq.com     
	 * 修改时间：2018年12月26日 上午12:05:16    
	 * 修改备注： 
	 * @return</pre>
	 */
	/*如何将服务端响应的json格式的字符串转换为对应的json对象？
	客户端处理：
	通过eval("("+json格式的字符串+")");使其转换为json对象。*/
	/*@RequestMapping("/product/deleteproduct")
	public void deleteproduct(Integer id,HttpServletResponse response){
		Map map=new HashMap<>();
		iProductService.deleteproduct(id);
		map.put("code",200);
		map.put("msg","success");
		String json = JSONObject.fromObject(map).toString();
		Outjson(json, response);
	}*/
	
	/**
	 * <pre>deleteproduct(
	 * 1.@ResponseBody注解是使用jackSon工具类【jackSon是apache下的】) 先导jar包
	 * 2.扫描@ResponseBody注解在spring-mvc-controller.xml进行扫描【】<mvc:annotation-driven/>
	 * 创建人：宋晓静       2208459187@qq.com    
	 * 创建时间：2019年1月9日 下午2:29:25    
	 * 修改人：宋晓静       2208459187@qq.com     
	 * 修改时间：2019年1月9日 下午2:29:25    
	 * 修改备注： 
	 * @param id
	 * @param response
	 * @return</pre>
	 */
	@RequestMapping("/product/deleteproduct")
	@ResponseBody
	//自定义属性(使用户可以看到执行的信息)
	@LogAnnotation("删除产品")
	public Map deleteproduct(Integer id,HttpServletResponse response){
		Map resultMap = new HashMap();
		try {
			iProductService.deleteproduct(id);
			resultMap.put("code",200);
			resultMap.put("msg","success");
		} catch (Exception e) {
			//系统错误【】就是代码上的错
			e.printStackTrace();
			resultMap.put("code",-1);
			resultMap.put("msg","error:"+e.getMessage());
		}
		return resultMap;
	}
	/**
	 * <pre>deleteBatch(批量删除)   
	 * 创建人：宋晓静       2208459187@qq.com    
	 * 创建时间：2018年12月26日 上午12:24:43    
	 * 修改人：宋晓静       2208459187@qq.com     
	 * 修改时间：2018年12月26日 上午12:24:43    
	 * 修改备注： 
	 * @param ids
	 * @return</pre>
	 */
	@RequestMapping("/product/deleteBatch")
	@ResponseBody
	//自定义属性(使用户可以看到执行的信息)
	@LogAnnotation("批量删除产品")
	public Map deleteBatch(String ids){
		Map<String,Object> map=new HashMap<String,Object>();
		try {
			iProductService.deleteBatch(ids);
			map.put("code",200);
			map.put("msg","ok");
		} catch (Exception e) {
			//系统错误【】就是代码上的错
			e.printStackTrace();
			map.put("code",-1);
			map.put("msg","error:"+e.getMessage());
		}
		return map;
	}
	/**
	 * <pre>toupdaproduct(回显)   
	 * 创建人：宋晓静       2208459187@qq.com    
	 * 创建时间：2018年12月26日 下午3:15:17    
	 * 修改人：宋晓静       2208459187@qq.com     
	 * 修改时间：2018年12月26日 下午3:15:17    
	 * 修改备注： 
	 * @return</pre>
	 */
	@RequestMapping("/product/toupdaproduct")
	public ModelAndView toupdaproduct(Integer id){
		ModelAndView mav=new ModelAndView("product/update");
		//普通的品牌查询做动态下拉框回显
		List<Brand> listbrand=iBrandService.listbrand();
		mav.addObject("list",listbrand);
		//产品信息的回显
		Product product=iProductService.toupdaproduct(id);
		mav.addObject("productinfo",product);
		//子图片的回显调用查询的方法
		List<ProductImage> productImages=iProductService.queryproductImage(id);
		mav.addObject("productImages",productImages);
		return mav;
	}
	
	/**
	 * <pre>updateproduct(修改)   
	 * 创建人：宋晓静       2208459187@qq.com    
	 * 创建时间：2018年12月26日 下午3:27:53    
	 * 修改人：宋晓静       2208459187@qq.com     
	 * 修改时间：2018年12月26日 下午3:27:53    
	 * 修改备注： 
	 * @param
	 * @return</pre>
	 */
	@RequestMapping("/product/updateproduct")
	//自定义属性(使用户可以看到执行的信息)
	@LogAnnotation("修改产品")
	public String updateproduct(Product product,
								@RequestParam MultipartFile shopFile,
								@RequestParam MultipartFile[] childsImages,
								HttpServletRequest request) throws IOException {
		//获取项目在硬盘上的跟目录【提取一个公共方法getRootPach()在BaseController类中】
		String rootPach = getRootPach(request);
		//主图片转换后的文件信息【提取一个公共方法convertFileInfo()在BaseController类中】
		FileInfo fileInfo = convertFileInfo(shopFile);
		//提取一个方法【在BaseController类中获取前台传的多张图片】
		List<FileInfo> fileInfos= convertFileInfoList(childsImages);
		//更新信息
		iProductService.updateproduct(product,fileInfo,rootPach,fileInfos);
		//并且重置到展示页面
		return "redirect:/product/toList.jhtml";
	}





	//导出Excel表【动态导出Excel表可以根据条件查询来进行导出】
	@RequestMapping("/product/exportExcelproduct")
	public void exportExcelproduct(Product product,HttpServletResponse response){
		//有条件查询但是没有分页的查询
		List<Product> listexcel=iProductService.exportExcelproduct(product);
		//将其商品信息转换为workbook格式【提取方法在本类中】
		XSSFWorkbook workbook = buildworkBook(listexcel);
		//下载
		FileUtil.excelDownload(workbook,response);
	}

	//将其转换为workbook格式
	private XSSFWorkbook buildworkBook(List<Product> listexcel) {
		 //将数据转换为指定的格式【将商品转换为Excel格式即workbook】
		 XSSFWorkbook workbook = new XSSFWorkbook();
		 //构建Sheet页【方法提取出来在本类中】
		 buildSheet(listexcel, workbook);
		 return workbook;
	}

    //构建Sheet页方法
	private void buildSheet(List<Product> listexcel, XSSFWorkbook workbook) {
		//给sheet页起名称【名称就是品牌表中的品牌名称】就从我们查询的数据里面取出来品牌名称
		XSSFSheet sheet = workbook.createSheet("商品表");
		//构建大标题合并行列
		buildTitle(sheet,workbook);
		//构建表头部分【方法在本类中】
		 buildHeader(sheet,workbook);
		 //构建表体内容【方法在本类中】
		 buildBody(listexcel,sheet,workbook);
	}

	//给大标题加样式方法
	private XSSFCellStyle buildTitleStyle(XSSFWorkbook workbook) {
		//给大标题加样式
		//调试样式水平居中和字体加粗
		XSSFCellStyle cellStyleTitle = workbook.createCellStyle();
		cellStyleTitle.setAlignment(HorizontalAlignment.CENTER);//设置水平居中
		cellStyleTitle.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中
		cellStyleTitle.setFillForegroundColor(HSSFColor.BLUE.index);//设置背景颜色
		cellStyleTitle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);//设置图案样式
		XSSFFont font = workbook.createFont();
		font.setColor(XSSFFont.COLOR_RED);//字体颜色
		font.setFontHeight(30);//字体大小
		font.setBold(true);//true是加粗
		cellStyleTitle.setFont(font);//将字体样式加入到style样式中
		return cellStyleTitle;
	}

	//构建大标题合并行列方法
	private void buildTitle(XSSFSheet sheet,XSSFWorkbook workbook) {
		//构建大标题
		XSSFCellStyle cellStyleTitle = buildTitleStyle(workbook);
		//创建一个行
		XSSFRow row = sheet.createRow(3);
		//一个列
		XSSFCell cell = row.createCell(7);
		//给单元格赋值
		cell.setCellValue("商品列表");
		//【合并行列的大标题】
		//合并单元格【四个参数firstRow 开始行 lastRow 结束行 firstCol开始列 lastCol结束列】
		CellRangeAddress rangeaddress=new CellRangeAddress(3,5,7,12);
		// 合并单元格
		sheet.addMergedRegion(rangeaddress);
        //给大标题加样式
		cell.setCellStyle(cellStyleTitle);

	}


	//构建表体内容方法
	private void buildBody(List<Product> listexcel, XSSFSheet sheet,XSSFWorkbook workbook) {
		int startRow=7;
		int startCell=6;
		XSSFCellStyle datacellStyle = workbook.createCellStyle();
		datacellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));

		XSSFCellStyle cellStylecolor = workbook.createCellStyle();
		cellStylecolor.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
		cellStylecolor.setFillForegroundColor(HSSFColor.RED.index);
		cellStylecolor.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		//for循环list集合
		for (int i=0;i<listexcel.size();i++){
			//行相当于一个javabean【for循环的下标是从0开始】
			Product productinfo= listexcel.get(i);
			//内容的第一行
			//【我们的for循环的下标是从0开始的,但是行的下标也是从0开始的
			// 所以下标是0的一行已经是标题了】
			//所以我们要在for循环中的行+1,就从下标为1的开始写内容
			XSSFRow row1 = sheet.createRow(i+startRow);
			//第一行
			XSSFCell cell1= row1.createCell(startCell+1);
			cell1.setCellValue(productinfo.getId());
			//第二行
			XSSFCell cell2= row1.createCell(startCell+2);
			cell2.setCellValue(productinfo.getProductName());
			//第三行
			XSSFCell cell3= row1.createCell(startCell+3);
			cell3.setCellValue(productinfo.getProductPrice());

			//第四行
			XSSFCell cell4= row1.createCell(startCell+4);
			cell4.setCellValue(productinfo.getInsertDate());
			cell4.setCellStyle(datacellStyle);//时间格式转换
			//第五行
			XSSFCell cell5= row1.createCell(startCell+5);
			cell5.setCellValue(productinfo.getUpdateDate());
			cell5.setCellStyle(datacellStyle);//时间格式转换
			//第六行
			XSSFCell cell6= row1.createCell(startCell+6);
			cell6.setCellValue(productinfo.getBrand().getBrandname());
			//调试背景样式
			XSSFCellStyle cellStyleTitle = workbook.createCellStyle();
			if (productinfo.getProductPrice()<100){
				cellStyleTitle.setFillForegroundColor(HSSFColor.RED.index);//设置背景颜色
				cellStyleTitle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);//设置图案样式
				cell1.setCellStyle(cellStyleTitle);
				cell2.setCellStyle(cellStyleTitle);
				cell3.setCellStyle(cellStyleTitle);
				cell4.setCellStyle(cellStylecolor);
				cell5.setCellStyle(cellStylecolor);
				cell6.setCellStyle(cellStyleTitle);
			}else {
				cell4.setCellStyle(datacellStyle);
				cell5.setCellStyle(datacellStyle);
			}
		}
	}


	//构建表头部分方法
	private void buildHeader(XSSFSheet sheet,XSSFWorkbook workbook) {
		//把标题定义在一个数组中方便再次增加
		String[] headers={"ID","商品名称","商品价格","录入时间","修改时间","品牌名称"};
		//给表头增加样式
		XSSFCellStyle cellStyleTitle = buidHeaderStyle(workbook);
		//创建行【行向右移动了6行】
		XSSFRow title = sheet.createRow(6);
		//for循环数组
		for (int i = 0; i < headers.length; i++) {
			//数组从0开始给单元格中赋值的
			String header = headers[i];
			//创建列,单元格
			XSSFCell titleCell = title.createCell(i+7);
			//给单元格赋值
			titleCell.setCellValue(header);
			titleCell.setCellStyle(cellStyleTitle);
		}
	}
	//给表头增加样式
	private XSSFCellStyle buidHeaderStyle(XSSFWorkbook workbook) {
		//给大标题加样式
		//调试样式水平居中和字体加粗
		XSSFCellStyle cellStyleTitle = workbook.createCellStyle();
		cellStyleTitle.setAlignment(HorizontalAlignment.CENTER);//设置水平居中
		cellStyleTitle.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中
		XSSFFont font = workbook.createFont();
		font.setBold(true);//true是加粗
		cellStyleTitle.setFont(font);//将字体样式加入到style样式中
		return cellStyleTitle;
	}


	/**
	 * Excel品牌导出数据
	 */
	@RequestMapping("/product/exportBrandExcel")
	public void  exportBrandExcel(Product product,HttpServletResponse response){
		//普通查询所有品牌信息【一、查询符合条件的数据】
		List<Brand> brandList = iBrandService.listbrand();
		//创建workbook工作簿
		XSSFWorkbook workbook = new XSSFWorkbook();
		//这句话主要起到给前台传的ID赋一个初始值【放到for循环外面是为了是-1就根据for循环】
		Integer brandid = product.getBrand().getId();
		//for循环品牌list集合
		for (int i = 0; i < brandList.size(); i++) {
			//每一条数据就是一个Java实体类【数据从下标为0开始的】
			Brand brandInfo = brandList.get(i);
			//判断前台是否条件查询品牌【方法在本类中】
			List<Product> productList = getBrand(product, brandid, brandInfo);
			//给sheet页起名称【名称就是品牌表中的品牌名称】就从我们查询的数据里面取出来品牌名称
			buildSheetBrand(workbook, brandInfo, productList);
		}
		//下载
		FileUtil.excelDownload(workbook, response);
	}
    //多个sheet页品牌导出信息
	private void buildSheetBrand(XSSFWorkbook workbook, Brand brandInfo, List<Product> productList) {
		XSSFSheet sheet = workbook.createSheet(brandInfo.getBrandname()+"【"+productList.size()+"】");
		//构建大标题合并行列
		buildTitle(sheet,workbook);
		//构建表头部分【方法在本类中】
		buildHeader(sheet,workbook);
		//每个品牌中对应的商品的信息【方法在本类中】
		buildBodyBrand(brandInfo, productList, sheet,workbook);
	}
	//判断前台是否条件查询品牌
	private List<Product> getBrand(Product product, Integer brandid, Brand brandInfo) {
		//定义productList为全局变量,赋一个初始值为空值
		List<Product> productList =null;
		//条件查询品牌【当前台没有条件查询时前台传一个-1就把所有品牌都导入出来】
		if (brandid==-1){
			//核心代码
			//从品牌查询的数据中取出来品牌的ID然后赋值给商品表中的branid字段中
			//实现的效果就是一个品牌的一条数据ID中有几条符合这个品牌ID的商品信息就展示出来
			product.getBrand().setId(brandInfo.getId());
			//条件查询和分页查询的商品信息
			productList = iProductService.querylist(product);
		}
		//否则不等于-1时就是条件查询品牌
		else {
			//这个不判断的话条件查询我只查一个品牌而其他的品牌也会出来而不会为0
			//这样判断从前台传过来的品牌ID 是否 和查询出来的品牌数据中的ID对应
			if (product.getBrand().getId()==brandInfo.getId()){
				productList = iProductService.querylist(product);
			}else {
				//否则不相等就从新赋值productList不能为空要不然商品的个数就都为空了
				productList = new ArrayList<Product>();
			}
		}
		return productList;
	}


	//每个品牌中对应的商品的信息方法
	private void buildBodyBrand(Brand brandInfo, List<Product> productList, XSSFSheet sheet,XSSFWorkbook workbook) {
		int startRow=7;
		int startCell=6;
		for (int j = 0; j < productList.size(); j++) {
			Product productInfo = productList.get(j);
			//内容
			XSSFRow row = sheet.createRow(j + startRow);
			XSSFCell cel1 = row.createCell(startCell+1);
			cel1.setCellValue(productInfo.getId());
			XSSFCell cel2 = row.createCell(startCell+2);
			cel2.setCellValue(productInfo.getProductName());
			XSSFCell cel3 = row.createCell(startCell+3);
			cel3.setCellValue(productInfo.getProductPrice());
			XSSFCell cel4 = row.createCell(startCell+4);
			cel4.setCellValue(DateUtils.formatDateTime(productInfo.getInsertDate()));
			XSSFCell cel5 = row.createCell(startCell+5);
			cel5.setCellValue(DateUtils.formatDateTime(productInfo.getUpdateDate()));

			XSSFCell cell6= row.createCell(startCell+6);
			cell6.setCellValue(brandInfo.getBrandname());
			//调试背景样式
			XSSFCellStyle cellStyleTitle = workbook.createCellStyle();
			if (productInfo.getProductPrice()<100){
				cellStyleTitle.setFillForegroundColor(HSSFColor.RED.index);//设置背景颜色
				cellStyleTitle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);//设置图案样式
				cel1.setCellStyle(cellStyleTitle);
				cel2.setCellStyle(cellStyleTitle);
				cel3.setCellStyle(cellStyleTitle);
				cel4.setCellStyle(cellStyleTitle);
				cel5.setCellStyle(cellStyleTitle);
				cell6.setCellStyle(cellStyleTitle);
			}
		}
	}

}
