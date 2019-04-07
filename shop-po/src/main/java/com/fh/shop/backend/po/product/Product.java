/** 
 * <pre>项目名称:shop-admin3 
 * 文件名称:Product.java 
 * 包名:com.fh.shop.backend.po.product 
 * 创建日期:2018年12月23日下午9:02:06 
 * Copyright (c) 2018, 1071980754@qq.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.po.product;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fh.shop.backend.po.Page;
import com.fh.shop.backend.po.brand.Brand;

/** 
 * <pre>项目名称：shop-admin3    
 * 类名称：Product    
 * 类描述：    
 * 创建人：宋晓静     2208459187  
 * 创建时间：2018年12月23日 下午9:02:06    
 * 修改人：宋晓静    2208459187  
 * 修改时间：2018年12月23日 下午9:02:06    
 * 修改备注：       
 * @version </pre>    
 */
public class Product extends Page implements Serializable{
	private static final long serialVersionUID = 5306087749856060545L;
    private Integer id;
	private String productName;
	private  Float productPrice;
	private  Float minprice;
	private  Float maxprice;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date insertDate;//当前时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date mininsertDate;//最小当前时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date maxinsertDate;//最大当前时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date updateDate;//修改时间  
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date minupdateDate;//最小修改时间  
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date maxupdateDate;//最大修改时间  
    
    //品牌表
    private Brand brand=new Brand();
    //多张图片修改上传ids
	private String ids;
	//图片
    private String image;
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Float getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Float productPrice) {
		this.productPrice = productPrice;
	}

	public Float getMinprice() {
		return minprice;
	}

	public void setMinprice(Float minprice) {
		this.minprice = minprice;
	}

	public Float getMaxprice() {
		return maxprice;
	}

	public void setMaxprice(Float maxprice) {
		this.maxprice = maxprice;
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

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	/* (non-Javadoc)    
	 * @see java.lang.Object#toString()    
	 */
	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName + ", productPrice=" + productPrice + ", minprice="
				+ minprice + ", maxprice=" + maxprice + ", insertDate=" + insertDate + ", mininsertDate="
				+ mininsertDate + ", maxinsertDate=" + maxinsertDate + ", updateDate=" + updateDate + ", minupdateDate="
				+ minupdateDate + ", maxupdateDate=" + maxupdateDate + ", brand=" + brand + "]";
	}
    
	
	
}
