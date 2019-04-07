/** 
 * <pre>项目名称:shop_admin_m1 
 * 文件名称:InfoAspect.java 
 * 包名:com.fh.shop.backend.aspect 
 * 创建日期:2019年1月18日下午3:30:44 
 * Copyright (c) 2019, 1071980754@qq.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.controller.aspect;


import com.fh.shop.backend.biz.log.ILogService;
import com.fh.shop.backend.common.ServerResponse;
import com.fh.shop.backend.controller.annotation.LogAnnotation;
import com.fh.shop.backend.po.log.Log;
import com.fh.shop.backend.util.SystemConstants;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fh.shop.backend.common.WebContext;
import com.fh.shop.backend.po.user.User;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

/** 
 * <pre>项目名称：shop_admin_m1    
 * 类名称：LogAspect    
 * 类描述：【普通的一个java类】
 * 创建人：宋晓静     2208459187  
 * 创建时间：2019年1月18日 下午3:30:44    
 * 修改人：宋晓静    2208459187  
 * 修改时间：2019年1月18日 下午3:30:44    
 * 修改备注：       
 * @version </pre>    
 */
public class LogAspect {
	//注入日志Service层【写日志新增】
	@Resource(name = "logService")
	private ILogService logService;

	//普通方法 【修饰符  返回值类型  方法名(参数)】
	//!!!{返回值不能变必须是Object,万事万物皆对象。方法名可以改变。
	//参数不可以改变必须是ProceedingJoinPoint}!!!
	//日志
	private static final Logger LOG=LoggerFactory.getLogger(LogAspect.class);
	public Object logAspect(ProceedingJoinPoint pjp){
		//动态获取类名
		String className = pjp.getTarget().getClass().getCanonicalName();
		//动态获取类中的方法名
		String methodName = pjp.getSignature().getName();
		//动态获取方法返回值
		MethodSignature  ms= (MethodSignature)pjp.getSignature();
	    String simpleName = ms.getReturnType().getSimpleName();

		Method method = ms.getMethod();
		String content ="";
        if (method.isAnnotationPresent(LogAnnotation.class)){
			LogAnnotation logAnnotation = method.getAnnotation(LogAnnotation.class);
			 content = logAnnotation.value();
		}
		Object result =null;
		 User userInfo =new User();
		try {
			//执行真正的核心业务代码方法
			//方法执行后的返回值
			long start= System.currentTimeMillis();//开始时间
			result = pjp.proceed();
			long end=System.currentTimeMillis();
		    userInfo = (User) WebContext.getRequest().getSession().getAttribute("user");
			//判断当userInfo不为空时
			Log log=new Log();
			if(userInfo!=null){
				//执行成功时
				LOG.info("{}执行这个{}的方法{}()成功",userInfo.getUserName(),className,methodName);
				log.setUserName(userInfo.getUserName());
			}
			log.setId(UUID.randomUUID().toString());
			log.setInfo("执行这个类"+className+"的方法"+methodName+"()成功");
			log.setStatus(SystemConstants.LOG_SUCCESS);
			log.setCreateTime(new Date());
			log.setExecuteTime(end-start);
			log.setUsercontent(content);
			logService.addlog(log);

		} catch (Throwable e) {
			//在控制台打印异常的详细信息
			e.printStackTrace();
			//从Session中取出用户名
			userInfo = (User) WebContext.getRequest().getSession().getAttribute("user");
			//执行失败时
			LOG.error("{}执行这个{}的{}()失败",userInfo.getUserName(),className,methodName,e.getMessage());
			//判断当userInfo不为空时
			Log log=new Log();
			log.setId(UUID.randomUUID().toString());
			log.setUserName(userInfo.getUserName());
			log.setInfo("执行这个类"+className+"的方法"+methodName+"()成功");
			log.setStatus(SystemConstants.LOG_ERROR);
			log.setCreateTime(new Date());
			log.setExecuteTime((long) SystemConstants.LOG_EXECUTETIME);
			log.setUsercontent(content);
			logService.addlog(log);
            if (simpleName.equalsIgnoreCase("string")){
            	return "/error";
			}else {
				return ServerResponse.error();
		}
		}
		return result;
	}
}
