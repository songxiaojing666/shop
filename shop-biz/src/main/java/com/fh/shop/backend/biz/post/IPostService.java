package com.fh.shop.backend.biz.post;

import com.fh.shop.backend.common.DataTableResult;
import com.fh.shop.backend.common.ServerResponse;
import com.fh.shop.backend.po.post.Post;
import com.fh.shop.backend.po.product.Product;

public interface IPostService {
    //查询用户列表
    DataTableResult findPostList(Post post);
    //查询
    ServerResponse findAreaList(Integer pid);

    //产品信息的回显
    Post toupdapost(Integer id);

    void updatepost(Post post);
}
