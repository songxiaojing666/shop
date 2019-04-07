package com.fh.shop.backend.controller.api.product;


import com.fh.shop.backend.api.product.ProductVo;
import com.fh.shop.backend.biz.product.IProductService;
import com.fh.shop.backend.common.ServerResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductControllerApi {

    @Resource(name = "product")
    private IProductService productService;

    //使用接口展示数据让用户看
    //method = {RequestMethod.POST,RequestMethod.GET}是一个数组及接收POST请求又接收GET请求
    @RequestMapping(value = "list",method = {RequestMethod.POST,RequestMethod.GET})
    public Object list(String callback){
        //判断callback这个参数为null时说明前台传的是POST请求,否则callback这个参数不为null时传的是GET请求
        List<ProductVo> productList=productService.list();
        if (StringUtils.isNotEmpty(callback)){
            //结合spring提供的MappingJacksonValue来将要响应的信息作为构造参数进行传递
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(ServerResponse.success(productList));
            //还要把callback返回过去
            mappingJacksonValue.setJsonpFunction(callback);
            return mappingJacksonValue;
        }else {
            return ServerResponse.success(productList);
        }

    }


}
