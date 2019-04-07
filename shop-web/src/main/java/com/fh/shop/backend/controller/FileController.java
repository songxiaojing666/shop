package com.fh.shop.backend.controller;

import com.fh.shop.backend.common.ServerResponse;
import com.fh.shop.backend.util.COSUtil;
import com.fh.shop.backend.util.FileUtil;
import com.fh.shop.backend.util.SystemConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

@RestController
public class FileController extends BaseController{

    @Autowired
     private HttpServletRequest request;

    @RequestMapping(value = "/file/uploadHeaderImage",method = RequestMethod.POST)
    public ServerResponse uploadHeaderImage(@RequestParam MultipartFile headerImage){
        InputStream inputStream =null;
        try {
           inputStream = headerImage.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filename = headerImage.getOriginalFilename();
        String uploadFilePath = COSUtil.uploadFile(inputStream, filename);
        return ServerResponse.success(uploadFilePath);
    }






}
