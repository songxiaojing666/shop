/** 
 * <pre>项目名称:shop_admin_m1 
 * 文件名称:LogServiceImpl.java 
 * 包名:com.fh.shop.backend.biz.log 
 * 创建日期:2019年1月10日下午10:21:04 
 * Copyright (c) 2019, 1071980754@qq.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.biz.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fh.shop.backend.mapper.log.ILogMapper;
import com.fh.shop.backend.po.log.Log;

import java.util.List;

/** 
 * <pre>项目名称：shop_admin_m1    
 * 类名称：LogServiceImpl    
 * 类描述：    
 * 创建人：宋晓静     2208459187  
 * 创建时间：2019年1月10日 下午10:21:04    
 * 修改人：宋晓静    2208459187  
 * 修改时间：2019年1月10日 下午10:21:04    
 * 修改备注：       
 * @version </pre>    
 */
@Service("logService")
public class LogServiceImpl implements ILogService{
	@Autowired
	private ILogMapper logMapper;
	/* 日志新增
	 */
	@Override
	public void addlog(Log log) {
		logMapper.addlog(log);
	}
	//获取分页的总条数
	@Override
	public Long listlog(Log log) {
		return logMapper.listlog(log);
	}
	//普通的条件查询和查询
	@Override
	public List<Log> findlog(Log log) {
		return logMapper.findlog(log);
	}


}
