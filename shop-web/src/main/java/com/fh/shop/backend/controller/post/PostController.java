package com.fh.shop.backend.controller.post;

import com.fh.shop.backend.biz.post.IPostService;
import com.fh.shop.backend.common.DataTableResult;
import com.fh.shop.backend.common.ServerResponse;

import com.fh.shop.backend.po.post.Post;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;


@Controller
@RequestMapping("/post")
public class PostController {

    @Resource(name = "postservice")
    private IPostService iPostService;
    //查询用户列表
    @RequestMapping(value = "findPostList",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse findPostList(Post post){
        DataTableResult postList = iPostService.findPostList(post);
        //System.out.println(postList);
        return ServerResponse.success(postList);
    }

    //跳转到查询页面
    @RequestMapping("toPostQuery")
    public String toPostQuery(){
        return "post/postList";
    }

    //查询
    @RequestMapping(value = "findAreaList", method =RequestMethod.POST)
    @ResponseBody
    public Object findAreaList(Integer pid,String callback) {
        ServerResponse findlist=iPostService.findAreaList(pid);
        if (StringUtils.isNotEmpty(callback)){
            //结合spring提供的MappingJacksonValue来将要响应的信息作为构造参数进行传递
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(findlist);
            //还要把callback返回过去
            mappingJacksonValue.setJsonpFunction(callback);
            return mappingJacksonValue;
        }else {
            return ServerResponse.success(findlist);
        }

    }

    //回显
    @RequestMapping(value = "toupdapost",method = RequestMethod.GET)
    public ModelAndView toupdapost(Integer id){
        ModelAndView mav=new ModelAndView("post/update");
        //产品信息的回显
        Post post=iPostService.toupdapost(id);
        mav.addObject("post",post);
        return mav;
    }

    //修改
    @RequestMapping("updatepost")
    public String updatepost(Post post) {
        iPostService.updatepost(post);
        return "redirect:toPostQuery.jhtml";
    }


}
