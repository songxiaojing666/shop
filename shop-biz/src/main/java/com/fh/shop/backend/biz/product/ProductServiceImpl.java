/** 
 * <pre>项目名称:shop-admin3 
 * 文件名称:ProductServiceImpl.java 
 * 包名:com.fh.shop.backend.biz.product 
 * 创建日期:2018年12月23日下午9:28:33 
 * Copyright (c) 2018, 1071980754@qq.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.biz.product;

import com.fh.shop.backend.biz.BaseService;
import com.fh.shop.backend.biz.image.IImageService;
import com.fh.shop.backend.common.DataTableResult;
import com.fh.shop.backend.common.FileInfo;
import com.fh.shop.backend.mapper.product.IProductMapper;
import com.fh.shop.backend.po.product.Product;
import com.fh.shop.backend.po.product.ProductImage;
import com.fh.shop.backend.util.FileUtil;
import com.fh.shop.backend.util.SystemConstants;
import com.fh.shop.backend.vo.ProductVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/** 
 * <pre>项目名称：shop-admin3    
 * 类名称：ProductServiceImpl    
 * 类描述：    
 * 创建人：宋晓静     2208459187  
 * 创建时间：2018年12月23日 下午9:28:33    
 * 修改人：宋晓静    2208459187  
 * 修改时间：2018年12月23日 下午9:28:33    
 * 修改备注：       
 * @version </pre>    
 */
@Service("product")//声明业务逻辑层的实现类,创建了Bean
//相当于注入了service的实现层<bean id="productServiceImpl" class="com.fh.shop.backend.biz.product.ProductServiceImpl"></bean>
public class ProductServiceImpl extends BaseService implements IProductService{
     /**
      * 注入dao的接口
      */
	  @Autowired
	  private IProductMapper iProductMapper;
	  //注入图片的Service接口
	@Autowired
	private IImageService iImageService;

	//根据定义DataTables所需的数据
	@Override
	public DataTableResult buildProductDataTable(Product product, Integer start, Integer length, Integer draw,HttpServletRequest request) {
		//构建排序信息
		buildorder(product, start, length,request);
		//获取分页的总条数
		Long pagelist=pagecountproduct(product);
		List<Product> productlist=querylist(product);
		//PO转VO
        List<ProductVo> productVolist=new ArrayList<ProductVo>();
        //循环从数据库中取到的po里面的内容
		for (Product productinfo : productlist) {
			//new一个Vo
			ProductVo productVo1 = new ProductVo();

			//将po中的展示数据赋值到vo中
			productVo1.setId(productinfo.getId());
			productVo1.setProductName(productinfo.getProductName());
			productVo1.setProductPrice(productinfo.getProductPrice());
			productVo1.setInsertDate(productinfo.getInsertDate());
			productVo1.setUpdateDate(productinfo.getUpdateDate());
			productVo1.setBrandname(productinfo.getBrand().getBrandname());
			productVo1.setImage(productinfo.getImage());
			productVolist.add(productVo1);
		}
		//用ServerResponse来做DataTables分页
		return DataTableResult.build(draw, pagelist, pagelist, productVolist);
	}
	//构建排序信息
	private void buildorder(Product product, Integer start, Integer length,HttpServletRequest request) {
		//第几个单元格  单元格从0开始
		String orderColumn = request.getParameter(SystemConstants.ORDER_COLUMN);
		//排序的顺序asc or desc
		String orderDir = request.getParameter(SystemConstants.DIR);
		//beanName是要排序的实体类的属性 不是字段名
		String beanName = request.getParameter(getColumnData(orderColumn));
		if (StringUtils.isNotEmpty(orderDir)){
			product.setSortfield(beanName);
			product.setSort(orderDir);
		}
		//给当前页下标赋值
		product.setStartPos(start);
		//给每页条数赋值
		product.setPageSize(length);
	}

	/**
	 * 新增
	 */
	@Override
	public void addproduct(Product product, FileInfo fileInfo, String rootPach, List<FileInfo> fileInfos){
		//单个图片上传
		addImage(product, fileInfo, rootPach);
		Date date=new Date();
		product.setInsertDate(date);
		product.setUpdateDate(date);
		iProductMapper.addproduct(product);
		//批量插入子页面【在本类中提取一个公共方法】
		addChildImgae(product, rootPach, fileInfos);
		//throw new RuntimeException("报错了");//运行时异常*/
	   /* 1.throw  new Exception("lallalla,l");非运行时异常  
	    *2.【SpringMvc默认的是运行时异常必须加上】rollback-for="Exception"所有异常都拦截出现异常
	    *3.时都会回滚
	    * */
			
	}


	/* 查询
	 */
	@Override
	public List<Product> querylist(Product product) {
		return iProductMapper.querylist(product);
	}
	/*删除
	 */
	@Override
	public void deleteproduct(Integer id) {
		iProductMapper.deleteproduct(id);
		//throw new RuntimeException("=====");
	}
	/* 批量删除
	 */
	@Override
	public void deleteBatch(String ids) {
		List<Integer> productlist=new ArrayList<Integer>();
		String[] idarr = ids.split(",");
		for (String id : idarr) {
			productlist.add(Integer.parseInt(id));
		}
		iProductMapper.deleteBatch(productlist);
		//throw new RuntimeException("fdvfbf");
	}
	/* 回显
	 */
	@Override
	public Product toupdaproduct(Integer id) {
		Product toupdaproduct = iProductMapper.toupdaproduct(id);
		return toupdaproduct;
	}
	/* 修改
	 */
	@Override
	public void updateproduct(Product product, FileInfo fileInfo,String rootPach,List<FileInfo> fileInfos) {
        //更新产品主图【提取方法】
		if(fileInfo!=null){
			updateProductMainImage(product, fileInfo, rootPach);
			//删除服务器和数据库中的子图片路径【在本类中提取了方法】
			deleteChildImage(product, rootPach);
			//批量插入子页面【在本类中提取一个公共方法】
			addChildImgae(product, rootPach, fileInfos);
		}
		//重新设置修改时间
		Date date=new Date();
		product.setUpdateDate(date);
		iProductMapper.updateproduct(product);
		//throw new RuntimeException("======报错异常");
	}
	//删除服务器和数据库中的图片路径
	private void deleteChildImage(Product product, String rootPach) {
		//获取前台要删除的图片id
		String ids = product.getIds();
		//判断从前台传的ids是否为空
		if (StringUtils.isNotEmpty(ids)){
			//删除旧的子图片
			//查询出ID所对的子图信息select...in...
			String[] idsArr = ids.substring(1).split(",");
			List<Integer> idList=new ArrayList<Integer>();
			//for循环数组
			for (String id : idsArr) {
				idList.add(Integer.parseInt(id));
			}
			//查询出商品ID所对的子图信息
			List<ProductImage> productImageList=iImageService.findImageById(idList);
			//循环删除服务器中的图片
			for (ProductImage productImage : productImageList) {
				FileUtil.deleteFile(rootPach+productImage.getImagePath());
			}
			//删除数据库表中的多图片路径信息
			iImageService.deletePach(idList);
		}
	}
	//批量插入子页面
	private void addChildImgae(Product product, String rootPach, List<FileInfo> fileInfos) {
		List<ProductImage> productImagelist=new ArrayList<ProductImage>();
		//上传新的子图片
		for (FileInfo info : fileInfos) {
			//输出流
			InputStream inputStream = info.getIo();
			//获取文件名
			String originalFilename = info.getFileName();
			//获取绝对路径自动创建文件夹【编译过后的web文件夹下自动创建一个文件夹】
				String realPath = rootPach + SystemConstants.SHOP_IMAGE_DUO;
				//拷贝到指定的文件夹下
				String filePath = FileUtil.copyFile(inputStream, originalFilename, realPath);
				//图片路径和图片名
				String Image = SystemConstants.SHOP_IMAGE_DUO + filePath;
				//new图片实体类
				ProductImage productImage = new ProductImage();
				//给图片路径赋值
				productImage.setImagePath(Image);
				//取出商品里面的id给图片中商品外键赋值
				productImage.setProudctId(product.getId());
			   //多张图片添加到数据库中只和数据库交互一次用list集合添加
			   productImagelist.add(productImage);
	}
		//修改时判断从前台传的list集合是否为空,为空就不走添加,不为空
		if(productImagelist.size()>0){
			//批量插入子页面
			iImageService.addBatchImage(productImagelist);
		}
	}
	//【更新产品的主图片】的方法
	public void updateProductMainImage(Product product, FileInfo fileInfo, String rootPach) {
		//【更新产品的主图片】
		//这个方法是判断前台是否修改图片了,修改过就把服务器上的图片删除,没有修改就不走这个判断里面的方法
		if (fileInfo.getSize()!=null&&fileInfo.getSize()>0) {
			//提取公共方法【在本类中提取】删除服务器中的旧图片
			deleteServerold(product, rootPach);

			//删除完服务器上的老图片再把新的图片添加【在本类中提取】
			addImage(product, fileInfo, rootPach);
		}
	}

	//删除完服务器上的老图片再把新的图片添加上方法
	public void addImage(Product product, FileInfo fileInfo, String rootPach) {
		//增加新图片,获取绝对路径自动把新文件名添加到文件夹中
		String ImageRealPach= rootPach + SystemConstants.SHOP_IMAGE_PATH;
		if(fileInfo!=null){
			//重新把文件拷贝到指定文件夹中
			String file1Pach = FileUtil.copyFile(fileInfo.getIo(), fileInfo.getFileName(), ImageRealPach);
			//三、更新数据库中的路径
			product .setImage(SystemConstants.SHOP_IMAGE_PATH+file1Pach);
		}
	}

	//这个方法是判断前台是否修改图片了,修改过就把服务器上的图片删除,没有修改就不走这个判断里面的方法
	public void deleteServerold(Product product, String rootPach) {
		//一、删除服务器中的旧图片
		String imagePach = product.getImage();//获取数据库中旧图片的相对路径
		//获取图片的绝对路径【跟目录加旧图片的相对路径】
		String  realPach= rootPach + imagePach;
		//删除服务器上所存在的文件,【写了一个公共方法写在了FileUtil类中】
		FileUtil.deleteFile(realPach);
	}
	/* 获取分页的总条数
	 */
	@Override
	public Long pagecountproduct(Product product) {
		return iProductMapper.pagecountproduct(product);
	}
	//动态导出Excel表有条件查询但是没有分页的查询
	@Override
	public List<Product> exportExcelproduct(Product product) {
		return iProductMapper.exportExcelproduct(product);
	}
	//查询子图片
	@Override
	public List<ProductImage> queryproductImage(Integer id) {
		return iProductMapper.queryproductImage(id);
	}
	//使用接口展示数据让用户看
	@Override
	public List<ProductVo> list() {
		List<Product> list = iProductMapper.list();
		//PO转VO
		List<ProductVo> list1 = new ArrayList<ProductVo>();
		//循环这个从数据库查出来集合
		for (Product produclistApi : list) {
			//new一个Vo
			ProductVo productVoApi = new ProductVo();
			//给vo中的属性赋值
			productVoApi.setId(produclistApi.getId());
			productVoApi.setProductName(produclistApi.getProductName());
			productVoApi.setProductPrice(produclistApi.getProductPrice());
			productVoApi.setBrandname(produclistApi.getBrand().getBrandname());
			productVoApi.setImage(produclistApi.getImage());
			//把po转换为vo放到集合中
			list1.add(productVoApi);
			System.out.println(list);
		}
		//返回一个集合
		return list1;
	}


}
