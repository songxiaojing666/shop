/** 
 * <pre>项目名称:shop-admin3 
 * 文件名称:IProductMapper.java 
 * 包名:com.fh.shop.backend.mapper.product 
 * 创建日期:2018年12月24日下午9:11:08 
 * Copyright (c) 2018, 1071980754@qq.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.mapper.product;

import java.util.List;

import com.fh.shop.backend.po.product.Product;
import com.fh.shop.backend.po.product.ProductImage;

/** 
 * <pre>项目名称：shop-admin3    
 * 类名称：IProductMapper    
 * 类描述：    
 * 创建人：宋晓静     2208459187  
 * 创建时间：2018年12月24日 下午9:11:08    
 * 修改人：宋晓静    2208459187  
 * 修改时间：2018年12月24日 下午9:11:08    
 * 修改备注：       
 * @version </pre>    
 */
public interface IProductMapper {

	/** <pre>addproduct(新增)   
	 * 创建人：宋晓静       2208459187@qq.com    
	 * 创建时间：2018年12月24日 下午9:12:56    
	 * 修改人：宋晓静       2208459187@qq.com     
	 * 修改时间：2018年12月24日 下午9:12:56    
	 * 修改备注： 
	 * @param product</pre>    
	 */
	public void addproduct(Product product);

	/** <pre>querylist(查询)   
	 * 创建人：宋晓静       2208459187@qq.com    
	 * 创建时间：2018年12月25日 下午11:56:50    
	 * 修改人：宋晓静       2208459187@qq.com     
	 * 修改时间：2018年12月25日 下午11:56:50    
	 * 修改备注： 
	 * @param product 
	 * @return</pre>    
	 */
	public List<Product> querylist(Product product);

	/** <pre>deleteproduct(删除)   
	 * 创建人：宋晓静       2208459187@qq.com    
	 * 创建时间：2018年12月26日 上午12:06:33    
	 * 修改人：宋晓静       2208459187@qq.com     
	 * 修改时间：2018年12月26日 上午12:06:33    
	 * 修改备注： 
	 * @param id</pre>    
	 */
	public void deleteproduct(Integer id);

	/** <pre>deleteBatch(批量删除)   
	 * 创建人：宋晓静       2208459187@qq.com    
	 * 创建时间：2018年12月26日 上午12:25:49    
	 * 修改人：宋晓静       2208459187@qq.com     
	 * 修改时间：2018年12月26日 上午12:25:49    
	 * 修改备注： 
	 * @param ids</pre>    
	 */
	public void deleteBatch(List<Integer> productlist);

	/** <pre>toupdaproduct(这里用一句话描述这个方法的作用)   
	 * 创建人：宋晓静       2208459187@qq.com    
	 * 创建时间：2018年12月26日 下午3:20:42    
	 * 修改人：宋晓静       2208459187@qq.com     
	 * 修改时间：2018年12月26日 下午3:20:42    
	 * 修改备注： 
	 * @param id
	 * @return</pre>    
	 */
	public Product toupdaproduct(Integer id);

	/** <pre>updateproduct(修改)   
	 * 创建人：宋晓静       2208459187@qq.com    
	 * 创建时间：2018年12月26日 下午3:29:24    
	 * 修改人：宋晓静       2208459187@qq.com     
	 * 修改时间：2018年12月26日 下午3:29:24    
	 * 修改备注： 
	 * @param product</pre>    
	 */
	public void updateproduct(Product product);

	/** <pre>pagecountproduct(获取分页的总条数)   
	 * 创建人：宋晓静       2208459187@qq.com    
	 * 创建时间：2018年12月27日 下午11:54:14    
	 * 修改人：宋晓静       2208459187@qq.com     
	 * 修改时间：2018年12月27日 下午11:54:14    
	 * 修改备注： 
	 * @param product</pre>    
	 * @return 
	 */
	public Long pagecountproduct(Product product);
	//态导出Excel表有条件查询但是没有分页的查询
    public List<Product> exportExcelproduct(Product product);
	//查询子图片
	public List<ProductImage> queryproductImage(Integer id);
	//使用接口展示数据让用户看
    List<Product> list();
}
