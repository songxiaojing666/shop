/** 
 * <pre>项目名称:shop-admin3 
 * 文件名称:IBrandMapper.java 
 * 包名:com.fh.shop.backend.mapper.brand 
 * 创建日期:2018年12月28日下午6:44:22 
 * Copyright (c) 2018, 1071980754@qq.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.mapper.brand;

import java.util.List;

import com.fh.shop.backend.po.brand.Brand;

/** 
 * <pre>项目名称：shop-admin3    
 * 类名称：IBrandMapper    
 * 类描述：持久层的接口    
 * 创建人：宋晓静     2208459187  
 * 创建时间：2018年12月28日 下午6:44:22    
 * 修改人：宋晓静    2208459187  
 * 修改时间：2018年12月28日 下午6:44:22    
 * 修改备注：       
 * @version </pre>    
 */
public interface IBrandMapper {

	/** <pre>findsearch(查询总条数)   
	 * 创建人：宋晓静       2208459187@qq.com    
	 * 创建时间：2018年12月28日 下午8:13:44    
	 * 修改人：宋晓静       2208459187@qq.com     
	 * 修改时间：2018年12月28日 下午8:13:44    
	 * 修改备注： 
	 * @param brand
	 * @return</pre>    
	 */
	public Long findsearch(Brand brand);

	/** <pre>querylistbrand(查询)   
	 * 创建人：宋晓静       2208459187@qq.com    
	 * 创建时间：2018年12月28日 下午8:21:37    
	 * 修改人：宋晓静       2208459187@qq.com     
	 * 修改时间：2018年12月28日 下午8:21:37    
	 * 修改备注： 
	 * @param brand
	 * @return</pre>    
	 */
	public List<Brand> querylistbrand(Brand brand);

	/** <pre>addlistbrand(新增保存到数据库)   
	 * 创建人：宋晓静       2208459187@qq.com    
	 * 创建时间：2018年12月28日 下午9:06:27    
	 * 修改人：宋晓静       2208459187@qq.com     
	 * 修改时间：2018年12月28日 下午9:06:27    
	 * 修改备注： 
	 * @param brand</pre>    
	 */
	public void addlistbrand(Brand brand);

	/** <pre>deletelistbrand(删除)   
	 * 创建人：宋晓静       2208459187@qq.com    
	 * 创建时间：2018年12月28日 下午9:25:57    
	 * 修改人：宋晓静       2208459187@qq.com     
	 * 修改时间：2018年12月28日 下午9:25:57    
	 * 修改备注： 
	 * @param id</pre>    
	 */
	public void deletelistbrand(Integer id);

	/** <pre>deletemore(批量删除  )   
	 * 创建人：宋晓静       2208459187@qq.com    
	 * 创建时间：2018年12月28日 下午9:40:08    
	 * 修改人：宋晓静       2208459187@qq.com     
	 * 修改时间：2018年12月28日 下午9:40:08    
	 * 修改备注： 
	 * @param list</pre>    
	 */
	public void deletemore(List<Integer> list);

	/** <pre>brandtoupdate(回显)   
	 * 创建人：宋晓静       2208459187@qq.com    
	 * 创建时间：2018年12月28日 下午10:06:22    
	 * 修改人：宋晓静       2208459187@qq.com     
	 * 修改时间：2018年12月28日 下午10:06:22    
	 * 修改备注： 
	 * @param id
	 * @return</pre>    
	 */
	public Brand brandtoupdate(Integer id);

	/** <pre>updatelistbrand(修改)   
	 * 创建人：宋晓静       2208459187@qq.com    
	 * 创建时间：2018年12月28日 下午10:24:02    
	 * 修改人：宋晓静       2208459187@qq.com     
	 * 修改时间：2018年12月28日 下午10:24:02    
	 * 修改备注： 
	 * @param brand</pre>    
	 */
	public void updatelistbrand(Brand brand);

	/** <pre>listbrand(普通的品牌查询做动态下拉框)   
	 * 创建人：宋晓静       2208459187@qq.com    
	 * 创建时间：2018年12月29日 下午2:29:41    
	 * 修改人：宋晓静       2208459187@qq.com     
	 * 修改时间：2018年12月29日 下午2:29:41    
	 * 修改备注： 
	 * @return</pre>    
	 */
	public List<Brand> listbrand();

}
