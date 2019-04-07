package com.fh.shop.backend.mapper.image;

import com.fh.shop.backend.po.product.ProductImage;

import java.util.List;

public interface IImageMapper {
    //查询出商品ID所对的子图信息
    public List<ProductImage> findImageById(List<Integer> idList);
    //删除数据库表中的多图片路径信息
    public void deletePach(List<Integer> idList);
   // //批量插入子页面
    public void addBatchImage(List<ProductImage> productImagelist);
}
