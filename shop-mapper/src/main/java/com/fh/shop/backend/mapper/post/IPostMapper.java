package com.fh.shop.backend.mapper.post;


import com.fh.shop.backend.po.area.Area;
import com.fh.shop.backend.po.post.Post;

import java.util.List;

public interface IPostMapper {
    Long findPostCount(Post post);

    List<Post> findPostList(Post post);

    List<Area> findAreaList(Integer pid);

    Post toupdapost(Integer id);

    void updatepost(Post post);
}
