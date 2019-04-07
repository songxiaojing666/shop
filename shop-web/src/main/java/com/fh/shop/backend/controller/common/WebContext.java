/** 
 * <pre>项目名称:shop_admin_m1 
 * 文件名称:WebConText.java 
 * 包名:com.fh.shop.backend.common 
 * 创建日期:2019年1月31日下午11:04:18 
 * Copyright (c) 2019, 1071980754@qq.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.controller.common;

import javax.servlet.http.HttpServletRequest;

/** 
 * <pre>项目名称：shop_admin_m1    
 * 类名称：WebConText    
 * 类描述:ThreadLocal类
 * 创建人：宋晓静     2208459187  
 * 创建时间：2019年1月31日 下午11:04:18    
 * 修改人：宋晓静    2208459187  
 * 修改时间：2019年1月31日 下午11:04:18    
 * 修改备注：       
 * @version </pre>    
 */
public class WebContext {
	//
	private static ThreadLocal<HttpServletRequest> requestContext=new ThreadLocal<HttpServletRequest>();
	
	public static void setRequest(HttpServletRequest request){
		requestContext.set(request);
	}
	
	public static  HttpServletRequest getRequest(){
		return requestContext.get();
	}
	
	public static void remove(){
		requestContext.remove();
	}

}
