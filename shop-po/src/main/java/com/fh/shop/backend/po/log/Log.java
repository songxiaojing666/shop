/** 
 * <pre>项目名称:shop_admin_m1 
 * 文件名称:Log.java 
 * 包名:com.fh.shop.backend.po.log 
 * 创建日期:2019年1月10日下午10:29:13 
 * Copyright (c) 2019, 1071980754@qq.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.po.log;

import com.fh.shop.backend.po.Page;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
/** 
 * <pre>项目名称：shop_admin_m1    
 * 类名称：Log    
 * 类描述：    
 * 创建人：宋晓静     2208459187  
 * 创建时间：2019年1月10日 下午10:29:13    
 * 修改人：宋晓静    2208459187  
 * 修改时间：2019年1月10日 下午10:29:13    
 * 修改备注：       
 * @version </pre>    
 */
public class Log  extends Page implements Serializable{
	private static final long serialVersionUID = 2332900587815556337L;
	//id
	private String id;
	//用户名
    private String userName;
    //操作内容
    private String info;
    //状态
	private Integer status;
    //操作时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
	//最小时间
	//操作时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date mincreateTime;
	//最大时间
	//操作时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date maxcreateTime;


    //执行时间    这是毫秒值
    private Long executeTime;
    //最小毫秒值
    private Long minexecuteTime;
    //最大毫秒值
    private Long maxexecuteTime;

    //用户可看操作内容
	private String usercontent;

	public Long getMinexecuteTime() {
		return minexecuteTime;
	}

	public void setMinexecuteTime(Long minexecuteTime) {
		this.minexecuteTime = minexecuteTime;
	}

	public Long getMaxexecuteTime() {
		return maxexecuteTime;
	}

	public void setMaxexecuteTime(Long maxexecuteTime) {
		this.maxexecuteTime = maxexecuteTime;
	}

	public Date getMincreateTime() {
		return mincreateTime;
	}
	public void setMincreateTime(Date mincreateTime) {
		this.mincreateTime = mincreateTime;
	}

	public Date getMaxcreateTime() {
		return maxcreateTime;
	}

	public void setMaxcreateTime(Date maxcreateTime) {
		this.maxcreateTime = maxcreateTime;
	}

	public String getUsercontent() {
		return usercontent;
	}

	public void setUsercontent(String usercontent) {
		this.usercontent = usercontent;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getExecuteTime() {
		return executeTime;
	}

	public void setExecuteTime(Long executeTime) {
		this.executeTime = executeTime;
	}
}
