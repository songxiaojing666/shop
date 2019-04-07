/** 
 * <pre>项目名称:shop_admin_m1 
 * 文件名称:ServerResponse.java 
 * 包名:com.fh.shop.backend.common 
 * 创建日期:2019年1月11日下午1:14:26 
 * Copyright (c) 2019, 1071980754@qq.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.controller.common;

import com.fh.shop.backend.common.ResponseEnum;

import java.io.Serializable;

/** 
 * <pre>项目名称：shop_admin_m1    
 * 类名称：ServerResponse    
 * 类描述：    
 * 创建人：宋晓静     2208459187  
 * 创建时间：2019年1月11日 下午1:14:26    
 * 修改人：宋晓静    2208459187  
 * 修改时间：2019年1月11日 下午1:14:26    
 * 修改备注：       
 * @version </pre>    
 */
public class ServerResponse implements Serializable{
	private static final long serialVersionUID = -1258103569327407795L;
	//私有化三个属性
	private int code;
	private String msg;
	private Object data;//因为data里面可以放list集合,map对象,
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	   
	/**    
	 * <pre>创建一个新的实例 ServerResponse.    
	 *    
	 * @param code
	 * @param msg
	 * @param data</pre>   
	 * 定义一个私有的有参构造方法 【私有方法只能在本类中调用,不能被其他类再调用】
	 * 
	 */
	private ServerResponse(int code, String msg, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	   
	/**
	 * 
	 * <pre>创建一个新的实例 ServerResponse.    
	 *    </pre>【默认的为无参构造方法】私有的无参构造方法
	 */
	private ServerResponse() {
	}
	
	/**
	 * 成功的时候定义一个方法其他用时直接调用
	 * 【并且方法是静态的】
	 */
	public static ServerResponse  success(){
		//通过有参构造方法赋值给前台响应值
	return new ServerResponse(com.fh.shop.backend.common.ResponseEnum.SUCCESS.getCode(), com.fh.shop.backend.common.ResponseEnum.SUCCESS.getMsg(),null);
	}
	
   /**
    * 失败的时候
    * 【并且方法是静态的】
    */
	public static  ServerResponse error(){
		//通过有参构造方法赋值给前台响应值
		return new ServerResponse(com.fh.shop.backend.common.ResponseEnum.ERROR.getCode(), com.fh.shop.backend.common.ResponseEnum.ERROR.getMsg(),null);
	}
	
	/**
	 *  逻辑错误的时候
	 * 【并且方法是静态的】
	 */
	public  static  ServerResponse error(int code, String msg){
		 return new ServerResponse(code,msg,null);
	}
	
	
	
	
	
	//和Enum连用
	public static  ServerResponse error(ResponseEnum responseEnum){
		 return new ServerResponse(responseEnum.getCode(),responseEnum.getMsg(),null);
	}
	/**
	 * <pre>success(往前台传值在动态下拉框用到了)   
	 * 创建人：宋晓静       2208459187@qq.com    
	 * 创建时间：2019年1月11日 下午4:59:15    
	 * 修改人：宋晓静       2208459187@qq.com     
	 * 修改时间：2019年1月11日 下午4:59:15    
	 * 修改备注： 
	 * @param data
	 * @return</pre>
	 */
	public static ServerResponse  success(Object data){
		//通过有参构造方法赋值给前台响应值
	return new ServerResponse(200,"登录成功",data);   
	}
	

}
