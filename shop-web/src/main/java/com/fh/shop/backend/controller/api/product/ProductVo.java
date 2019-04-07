package com.fh.shop.backend.controller.api.product;

import java.io.Serializable;

public class ProductVo implements Serializable {
    private static final long serialVersionUID = -4423619854349779737L;
    private Integer id;
    private String productName;
    private  Float productPrice;
    //品牌表
    private String brandname;
    //图片
    private String image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getBrandname() {
        return brandname;
    }

    public void setBrandname(String brandname) {
        this.brandname = brandname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
