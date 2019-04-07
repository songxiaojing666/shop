/** 
 * <pre>项目名称:shop_admin_m1 
 * 文件名称:ILogMapper.java 
 * 包名:com.fh.shop.backend.mapper.log 
 * 创建日期:2019年1月10日下午10:23:28 
 * Copyright (c) 2019, 1071980754@qq.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.mapper.log;

import com.fh.shop.backend.po.log.Log;

import java.util.List;

/** 
 * <pre>项目名称：shop_admin_m1    
 * 类名称：ILogMapper    
 * 类描述：    
 * 创建人：宋晓静     2208459187  
 * 创建时间：2019年1月10日 下午10:23:28    
 * 修改人：宋晓静    2208459187  
 * 修改时间：2019年1月10日 下午10:23:28    
 * 修改备注：       
 * @version </pre>    
 */
public interface ILogMapper {

	/** 日志新增
	 */
	public void addlog(Log log);
	//获取分页的总条数
	Long listlog(Log log);
	//普通的条件查询和查询
	List<Log> findlog(Log log);
}
