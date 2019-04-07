/** 
 * <pre>项目名称:shop-admin3 
 * 文件名称:IBrandService.java 
 * 包名:com.fh.shop.backend.biz.brand 
 * 创建日期:2018年12月28日下午6:40:19 
 * Copyright (c) 2018, 1071980754@qq.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.biz.brand;

import java.util.List;
import java.util.Map;

import com.fh.shop.backend.common.DataTableResult;
import com.fh.shop.backend.po.brand.Brand;

/** 
 * <pre>项目名称：shop-admin3    
 * 类名称：IBrandService    
 * 类描述：  service的接口  
 * 创建人：宋晓静     2208459187  
 * 创建时间：2018年12月28日 下午6:40:19    
 * 修改人：宋晓静    2208459187  
 * 修改时间：2018年12月28日 下午6:40:19    
 * 修改备注：       
 * @version </pre>    
 */
public interface IBrandService {
	//构建DataTables所需的数据
	public DataTableResult buildBrandDataTable(Brand brand, Integer start, Integer length, Integer draw, Map<String, String[]> parameterMap);


		/** <pre>findsearch(查询总条数)
         * 创建人：宋晓静       2208459187@qq.com
         * 创建时间：2018年12月28日 下午8:12:45
         * 修改人：宋晓静       2208459187@qq.com
         * 修改时间：2018年12月28日 下午8:12:45
         * 修改备注：
         * @param brand
         * @return</pre>
         */
	public Long findsearch(Brand brand);

	/** <pre>querylistbrand(查询)   
	 * 创建人：宋晓静       2208459187@qq.com    
	 * 创建时间：2018年12月28日 下午8:20:27    
	 * 修改人：宋晓静       2208459187@qq.com     
	 * 修改时间：2018年12月28日 下午8:20:27    
	 * 修改备注： 
	 * @param brand
	 * @return</pre>    
	 */
	public List<Brand> querylistbrand(Brand brand);

	/** <pre>addlistbrand(新增保存到数据库)   
	 * 创建人：宋晓静       2208459187@qq.com    
	 * 创建时间：2018年12月28日 下午9:04:55    
	 * 修改人：宋晓静       2208459187@qq.com     
	 * 修改时间：2018年12月28日 下午9:04:55    
	 * 修改备注： 
	 * @param brand</pre>    
	 */
	public void addlistbrand(Brand brand);

	/** <pre>deletelistbrand(删除)   
	 * 创建人：宋晓静       2208459187@qq.com    
	 * 创建时间：2018年12月28日 下午9:25:04    
	 * 修改人：宋晓静       2208459187@qq.com     
	 * 修改时间：2018年12月28日 下午9:25:04    
	 * 修改备注： 
	 * @param id</pre>    
	 */
	public void deletelistbrand(Integer id);

	/** <pre>deletemore(批量删除)   
	 * 创建人：宋晓静       2208459187@qq.com    
	 * 创建时间：2018年12月28日 下午9:38:29    
	 * 修改人：宋晓静       2208459187@qq.com     
	 * 修改时间：2018年12月28日 下午9:38:29    
	 * 修改备注： 
	 * @param ids</pre>    
	 */
	public void deletemore(String ids);

	/** <pre>brandtoupdate(回显)   
	 * 创建人：宋晓静       2208459187@qq.com    
	 * 创建时间：2018年12月28日 下午10:05:39    
	 * 修改人：宋晓静       2208459187@qq.com     
	 * 修改时间：2018年12月28日 下午10:05:39    
	 * 修改备注： 
	 * @param id
	 * @return</pre>    
	 */
	public Brand brandtoupdate(Integer id);

	/** <pre>updatelistbrand(修改)   
	 * 创建人：宋晓静       2208459187@qq.com    
	 * 创建时间：2018年12月28日 下午10:23:20    
	 * 修改人：宋晓静       2208459187@qq.com     
	 * 修改时间：2018年12月28日 下午10:23:20    
	 * 修改备注： 
	 * @param brand</pre>    
	 */
	public void updatelistbrand(Brand brand);

	/** <pre>listbrand(普通的品牌查询做动态下拉框)   
	 * 创建人：宋晓静       2208459187@qq.com    
	 * 创建时间：2018年12月29日 下午2:28:01    
	 * 修改人：宋晓静       2208459187@qq.com     
	 * 修改时间：2018年12月29日 下午2:28:01    
	 * 修改备注： 
	 * @return</pre>    
	 */
	public List<Brand> listbrand();

}
