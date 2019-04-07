package com.fh.shop.backend.po.product;

import java.io.Serializable;
public class ProductImage implements Serializable{
    //生成序列号
	private static final long serialVersionUID = 409678208990644008L;
	//图片Id
	private Integer id;

	//图片路径
	private String imagePath;

	//图片外键  对应  商品主键
	private Integer proudctId;




	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Integer getProudctId() {
		return proudctId;
	}

	public void setProudctId(Integer proudctId) {
		this.proudctId = proudctId;
	}
}
