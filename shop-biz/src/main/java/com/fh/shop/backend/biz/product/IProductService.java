/** 
 * <pre>项目名称:shop-admin3 
 * 文件名称:IProductService.java 
 * 包名:com.fh.shop.backend.biz.product 
 * 创建日期:2018年12月23日下午9:29:17 
 * Copyright (c) 2018, 1071980754@qq.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.biz.product;

import java.util.List;

import com.fh.shop.backend.common.DataTableResult;
import com.fh.shop.backend.common.FileInfo;
import com.fh.shop.backend.po.product.ProductImage;
import com.fh.shop.backend.po.product.Product;
import com.fh.shop.backend.vo.ProductVo;

import javax.servlet.http.HttpServletRequest;

/** 
 * <pre>项目名称：shop-admin3    
 * 类名称：IProductService    
 * 类描述：    
 * 创建人：宋晓静     2208459187  
 * 创建时间：2018年12月23日 下午9:29:17    
 * 修改人：宋晓静    2208459187  
 * 修改时间：2018年12月23日 下午9:29:17    
 * 修改备注：       
 * @version </pre>    
 */
public interface IProductService {
	//构建DataTables所需的数据
	public DataTableResult buildProductDataTable(Product product, Integer start, Integer length, Integer draw, HttpServletRequest request);


		/**新增
         * 创建人：宋晓静       2208459187@qq.com
         * 创建时间：2018年12月23日 下午9:30:32
         * 修改人：宋晓静       2208459187@qq.com
         * 修改时间：2018年12月23日 下午9:30:32
         * 修改备注：
         *
         */
	public void addproduct(Product product, FileInfo fileInfo, String rootPach, List<FileInfo> fileInfos);

	/** <pre>querylist(查询)   
	 * 创建人：宋晓静       2208459187@qq.com    
	 * 创建时间：2018年12月25日 下午11:55:34    
	 * 修改人：宋晓静       2208459187@qq.com     
	 * 修改时间：2018年12月25日 下午11:55:34    
	 * 修改备注： 
	 * @param product 
	 * @return</pre>    
	 */
	public List<Product> querylist(Product product);

	/** <pre>deleteproduct(删除)   
	 * 创建人：宋晓静       2208459187@qq.com    
	 * 创建时间：2018年12月26日 上午12:05:53    
	 * 修改人：宋晓静       2208459187@qq.com     
	 * 修改时间：2018年12月26日 上午12:05:53    
	 * 修改备注： 
	 * @param id</pre>    
	 */
	public void deleteproduct(Integer id);

	/** <pre>deleteBatch(批量删除)   
	 * 创建人：宋晓静       2208459187@qq.com    
	 * 创建时间：2018年12月26日 上午12:25:03    
	 * 修改人：宋晓静       2208459187@qq.com     
	 * 修改时间：2018年12月26日 上午12:25:03    
	 * 修改备注： 
	 * @param ids</pre>    
	 */
	public void deleteBatch(String ids);

	/** <pre>toupdaproduct(回显)   
	 * 创建人：宋晓静       2208459187@qq.com    
	 * 创建时间：2018年12月26日 下午3:19:20    
	 * 修改人：宋晓静       2208459187@qq.com     
	 * 修改时间：2018年12月26日 下午3:19:20    
	 * 修改备注： 
	 * @param id
	 * @return</pre>    
	 */
	public Product toupdaproduct(Integer id);

	/** <pre>updateproduct(修改)   
	 * 创建人：宋晓静       2208459187@qq.com    
	 * 创建时间：2018年12月26日 下午3:28:29    
	 * 修改人：宋晓静       2208459187@qq.com     
	 * 修改时间：2018年12月26日 下午3:28:29    
	 * 修改备注： 
	 * @param product</pre>    
	 */
	public void updateproduct(Product product, FileInfo fileInfo, String rootPach, List<FileInfo> fileInfos);

	/** <pre>pagecountproduct(获取分页的总条数)   
	 * 创建人：宋晓静       2208459187@qq.com    
	 * 创建时间：2018年12月27日 下午11:53:30    
	 * 修改人：宋晓静       2208459187@qq.com     
	 * 修改时间：2018年12月27日 下午11:53:30    
	 * 修改备注： 
	 * @param product
	 * @return</pre>    
	 */
	public Long pagecountproduct(Product product);
	//动态导出Excel表有条件查询但是没有分页的查询
    public List<Product> exportExcelproduct(Product product);
	//查询子图片
	public List<ProductImage> queryproductImage(Integer id);
	//使用接口展示数据让用户看
	List<ProductVo> list();
}
