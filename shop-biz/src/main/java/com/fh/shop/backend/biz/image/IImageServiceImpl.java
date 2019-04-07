package com.fh.shop.backend.biz.image;

import com.fh.shop.backend.mapper.image.IImageMapper;
import com.fh.shop.backend.po.product.ProductImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("imageService")
public class IImageServiceImpl implements IImageService {
    @Autowired
    private IImageMapper iImageMapper;

    @Override
    public List<ProductImage> findImageById(List<Integer> idList) {
        //查询出商品ID所对的子图信息
        return iImageMapper.findImageById(idList);
    }

    @Override
    public void deletePach(List<Integer> idList) {
        //删除数据库表中的多图片路径信息
        iImageMapper.deletePach(idList);
    }
    //批量插入子页面
    @Override
    public void addBatchImage(List<ProductImage> productImagelist) {
        iImageMapper.addBatchImage(productImagelist);
    }
}
