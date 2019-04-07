/** 
 * <pre>项目名称:shop-admin1 
 * 文件名称:BaseController.java 
 * 包名:com.fh.shop.backend.controller 
 * 创建日期:2018年12月27日上午9:10:25 
 * Copyright (c) 2018, 1071980754@qq.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fh.shop.backend.common.FileInfo;
import com.fh.shop.backend.po.log.Log;
import com.fh.shop.backend.po.user.User;
import com.fh.shop.backend.util.DateUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/** 
 * <pre>项目名称：删除时响应客户端的一个状态
 * 类名称：BaseController    
 * 类描述：    
 * 创建人：宋晓静     2208459187  
 * 创建时间：2018年12月27日 上午9:10:25    
 * 修改人：宋晓静    2208459187  
 * 修改时间：2018年12月27日 上午9:10:25    
 * 修改备注：       
 * @version </pre>    
 */
public class BaseController {
	//获取项目的跟目录【protected只能继承子类才能访问】
	protected String getRootPach(HttpServletRequest request){
		//获取项目的跟目录找到图片所在的绝对路径
		String realPath = request.getServletContext().getRealPath("/");
		return realPath;
	}

	//给文件的实体类【Io流,文件名,文件大小】转换后的文件信息方法
	protected FileInfo convertFileInfo(@RequestParam MultipartFile shopFile) throws IOException {
		//文件的实体类【Io流,文件名,文件大小】
		FileInfo fileInfo=new FileInfo();
		//【通过前台传的name名来获取Io流,文件名,文件大小并且再赋值给它们】
		//给IO流赋值
		//输出流【通过流的方式读取到临时文件夹中】
		if(shopFile.getSize()>0){
			fileInfo.setIo(shopFile.getInputStream());//抛异常throws IOException
			//给文件名赋值,把名字变为UUID形式
			fileInfo.setFileName(shopFile.getOriginalFilename());
			//给图片大小赋值
			fileInfo.setSize(shopFile.getSize());
		}

		return fileInfo;
	}
    //获取前台传的多张图片把数组中的图片循环放到list集合中
	protected List<FileInfo> convertFileInfoList(MultipartFile[] childsImages) throws IOException {
		List<FileInfo> fileInfos=new ArrayList<FileInfo>();
		for (MultipartFile ChildsImages : childsImages) {
			if (ChildsImages.getSize()>0) {
				fileInfos.add(convertFileInfo(ChildsImages));
			}
		}
		return fileInfos;
	}







	public void Outjson(String json,HttpServletResponse response){
		   PrintWriter writer=null;//pu 润  t  wai ter
		  try {
			//如何将服务端响应的json格式的字符串转换为对应的json对象？
			    // 服务端处理：
				// 设置响应内容的类型并制定编码格式
			//response.setContentType("application/json;charset=utf-8");
			 //通过 response获取writer
			 writer=response.getWriter();
			 //通过writer将字符串响应给客户端(浏览器)
			 writer.write(json);
			 //清空 
			 writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//关闭流
			if(null !=writer){
				writer.close();
				writer=null;
			}
		}
	}

}
