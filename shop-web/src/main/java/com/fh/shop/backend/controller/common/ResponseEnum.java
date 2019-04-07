/** 
 * <pre>项目名称:shop_admin_m1 
 * 文件名称:ResponseEnum.java 
 * 包名:com.fh.shop.backend.common 
 * 创建日期:2019年1月11日下午4:01:48 
 * Copyright (c) 2019, 1071980754@qq.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.controller.common;

/** 
 * <pre>项目名称：shop_admin_m1    
 * 类名称：ResponseEnum    
 * 类描述：    
 * 创建人：宋晓静     2208459187  
 * 创建时间：2019年1月11日 下午4:01:48    
 * 修改人：宋晓静    2208459187  
 * 修改时间：2019年1月11日 下午4:01:48    
 * 修改备注：       
 * @version </pre>    
 */
public enum ResponseEnum {
	
	USERNAME_USERPWD_ERROR(1000,"用户名和密码和验证码为空"),
	USERNAME_ERROR(1001,"用户名错误"),
	USERPED_ERROR(1002,"密码错误"),
	USERIMAGE_ERROR(1003,"验证码错误"),
	USER_NAME_ERROR(1004,"该用户已存在"),
	USER_COUNT_ERROR(1005,"该账号已被锁定,请联系管理员"),
	ERROR(-1,"ERROR"),
	Parameter_Info(9999,"参数错误"),
	SUCCESS(200,"OK");


	
	private int code;
	private String msg;
	public int getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}
	   
	/**    
	 * <pre>创建一个新的实例 ResponseEnum.    
	 * @param code
	 * @param msg</pre>   
	 *  【私有的有参方法】
	 */
	private ResponseEnum(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	   
	/**    
	 * <pre>创建一个新的实例 ResponseEnum.    
	 *  【私有的无参方法】
	 */
	  private ResponseEnum() {
	 }
	   
	

}
