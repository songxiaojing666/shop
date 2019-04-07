/** 
 * <pre>项目名称:shop-admin3 
 * 文件名称:Brand.java 
 * 包名:com.fh.shop.backend.po.brand 
 * 创建日期:2018年12月28日下午6:39:13 
 * Copyright (c) 2018, 1071980754@qq.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.po.brand;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fh.shop.backend.po.Page;

/** 
 * <pre>项目名称：shop-admin3    
 * 类名称：Brand    
 * 类描述： 实体类 
 * 创建人：宋晓静     2208459187  
 * 创建时间：2018年12月28日 下午6:39:13    
 * 修改人：宋晓静    2208459187  
 * 修改时间：2018年12月28日 下午6:39:13    
 * 修改备注：       
 * @version </pre>    
 */
public class Brand extends Page implements Serializable{
	private static final long serialVersionUID = -8570656101464130594L;
	private Integer id;   //id,
	private String brandname; //品牌名
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date  insertDate;//品牌录入时间
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date  mininsertDate;//最小录入时间
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date  maxinsertDate;//最大录入时间
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updateDate; //品牌修改时间
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date minupdateDate;//最小修改时间
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date maxupdateDate;//最大修改时间

	private String insertDateTime;
	private String updateDateTime;

	public String getInsertDateTime() {
		return insertDateTime;
	}
	public void setInsertDateTime(String insertDateTime) {
		this.insertDateTime = insertDateTime;
	}
	public String getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(String updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBrandname() {
		return brandname;
	}
	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}
	public Date getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	public Date getMininsertDate() {
		return mininsertDate;
	}
	public void setMininsertDate(Date mininsertDate) {
		this.mininsertDate = mininsertDate;
	}
	public Date getMaxinsertDate() {
		return maxinsertDate;
	}
	public void setMaxinsertDate(Date maxinsertDate) {
		this.maxinsertDate = maxinsertDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Date getMinupdateDate() {
		return minupdateDate;
	}
	public void setMinupdateDate(Date minupdateDate) {
		this.minupdateDate = minupdateDate;
	}
	public Date getMaxupdateDate() {
		return maxupdateDate;
	}
	public void setMaxupdateDate(Date maxupdateDate) {
		this.maxupdateDate = maxupdateDate;
	}
	/* (non-Javadoc)    
	 * @see java.lang.Object#toString()    
	 */
	@Override
	public String toString() {
		return "Brand [id=" + id + ", brandname=" + brandname + ", insertDate=" + insertDate + ", mininsertDate="
				+ mininsertDate + ", maxinsertDate=" + maxinsertDate + ", updateDate=" + updateDate + ", minupdateDate="
				+ minupdateDate + ", maxupdateDate=" + maxupdateDate + "]";
	}
	

}
