/** 
 * <pre>项目名称:shop-admin 
 * 文件名称:LoginInterceptor.java 
 * 包名:com.fh.shop.interceptor 
 * 创建日期:2018年4月17日下午4:51:22 
 * Copyright (c) 2018, yuxy123@gmail.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.controller.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fh.shop.backend.po.user.User;
import com.fh.shop.backend.util.CookieUtil;
import com.fh.shop.backend.util.RedisUtils;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fh.shop.backend.util.SystemConstants;



/**
 * 
 * <pre>项目名称：shop-admin3    
 * 类名称：LoginInterceptor    
 * 类描述：  拦截器的工具类  
 * 创建人：宋晓静     2208459187  
 * 创建时间：2019年1月7日 下午9:21:37    
 * 修改人：宋晓静    2208459187  
 * 修改时间：2019年1月7日 下午9:21:37    
 * 修改备注：       
 * @version </pre>
 */
public class LoginInterceptor extends HandlerInterceptorAdapter{
       /* (non-Javadoc)    
     * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)    
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
    		throws Exception {
    	//上两个路径一样/user/loguser.jhtml
    	String servletPath = request.getServletPath();
    	String requestURI = request.getRequestURI();
    	//这个路径更详细http://localhost:8080/user/loguser.jhtml
    	StringBuffer requestURL = request.getRequestURL();
    	//System.out.println(servletPath+"......"+requestURI+"!!!!!"+requestURL);
    	//判断当遇到loguser.jhtml的不拦截继续执行
		//对不走拦截器的多个路径放行写成一个字符串用,分割循环放行
		String[] split = SystemConstants.WHITE_URL.split(",");
		for (String s : split) {
			if(requestURI.endsWith(s)){
				return true;
			}
		}
    	//现在所有的请求都被拦截了
    	//从Sesssion中取出来返回值为Object的
    	//Object userInfo = request.getSession().getAttribute(SystemConstants.USER_SESSION);
		//写session
		String fh_id = CookieUtil.readCookie(request, "fh_id");
		if (StringUtils.isEmpty(fh_id)){
			//登录失败后跳到登录页面
			response.sendRedirect(SystemConstants.USER_LOG);
			//当等于null时登录失败终止执行返回false
			return false;//禁止后续访问
		}

		String userJson = RedisUtils.get("user:" + fh_id);
		//续命
		RedisUtils.expire("user:" + fh_id,30*60);
		Gson gson = new Gson();
		User user = gson.fromJson(userJson, User.class);
        request.getSession().setAttribute(SystemConstants.USER_SESSION,user);
		if(!StringUtils.isEmpty(userJson)){
    		//当不等于null时登录成功就继续执行返回true
    		return true;
    	}else {
    		//登录失败后跳到登录页面
    		response.sendRedirect(SystemConstants.USER_LOG);
    		//当等于null时登录失败终止执行返回false
			return false;
		}
    }
	
}
