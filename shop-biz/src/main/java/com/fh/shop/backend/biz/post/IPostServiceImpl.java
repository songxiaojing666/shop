package com.fh.shop.backend.biz.post;

import com.fh.shop.backend.common.DataTableResult;
import com.fh.shop.backend.common.ResponseEnum;
import com.fh.shop.backend.common.ServerResponse;
import com.fh.shop.backend.mapper.post.IPostMapper;
import com.fh.shop.backend.po.area.Area;
import com.fh.shop.backend.po.post.Post;
import com.fh.shop.backend.po.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("postservice")
public class IPostServiceImpl implements IPostService {
    @Autowired
    private IPostMapper iPostMapper;
    //查询用户列表
    @Override
    public DataTableResult findPostList(Post post) {
        //获取总条数
        Long totalCount=iPostMapper.findPostCount(post);
        //获取数据
        List<Post> postlist=iPostMapper.findPostList(post);
//        for (Post post1 : postlist) {
//            post1.setAddress(post1.getProvince()+post1.getCity()+post1.getCountry());
//        }
        //组装结果
        DataTableResult result = DataTableResult.build(post.getDraw(), totalCount, totalCount, postlist);
        return result;
    }

    //查询
    @Override
    public ServerResponse findAreaList(Integer pid) {
            if (pid==null || pid<0){
                return ServerResponse.error(ResponseEnum.Parameter_Info);
            }
            List<Area> areaList = iPostMapper.findAreaList(pid);
            return ServerResponse.success(areaList);

    }

    //产品信息的回显
    @Override
    public Post toupdapost(Integer id) {
        Post toupdapost = iPostMapper.toupdapost(id);
        return  toupdapost;
    }

    //修改
    @Override
    public void updatepost(Post post) {
        iPostMapper.updatepost(post);
    }


}
