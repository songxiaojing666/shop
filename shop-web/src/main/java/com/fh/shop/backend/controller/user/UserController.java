/** 
 * <pre>项目名称:shop-admin3 
 * 文件名称:UserController.java 
 * 包名:com.fh.shop.backend.controller.user 
 * 创建日期:2019年1月7日下午3:55:17 
 * Copyright (c) 2019, 1071980754@qq.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.controller.user;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fh.shop.backend.biz.dept.IDeptService;
import com.fh.shop.backend.common.DataTableResult;
import com.fh.shop.backend.common.ResponseEnum;
import com.fh.shop.backend.common.ServerResponse;
import com.fh.shop.backend.po.dept.Dept;
import com.fh.shop.backend.util.*;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fh.shop.backend.biz.user.IUserService;
import com.fh.shop.backend.po.user.User;


/** 
 * <pre>项目名称：shop-admin3    
 * 类名称：UserController    
 * 类描述：    
 * 创建人：宋晓静     2208459187  
 * 创建时间：2019年1月7日 下午3:55:17    
 * 修改人：宋晓静    2208459187  
 * 修改时间：2019年1月7日 下午3:55:17    
 * 修改备注：       
 * @version </pre>    
 */
@Controller//声明当前类为控制层
@RequestMapping("/user/")
public class UserController{
	//注入Service接口
	@Resource
	private IUserService iUserService;
	/*查询
	 * 【登录】
	 * */
	/**登录方法(一种方式登录是最繁琐的)   
	 * 创建人：宋晓静       2208459187@qq.com    
	 * 创建时间：2019年1月7日 下午5:11:28    
	 * 修改人：宋晓静       2208459187@qq.com     
	 * 修改时间：2019年1月7日 下午5:11:28    
	 * 修改备注： 
	 * @param user
	 * @param request
	 * @return</pre>    
	 */
	/*@RequestMapping("loguser")
	@ResponseBody
	public Map loguser(User user){
		Map result=new HashMap<>();
		//获取前台传过来的值
		String username = user.getUserName();
		String userpwd = user.getUserPassword();
		 //先判断从前台传的值不为空
		 if(StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(userpwd)){
			 //【验证成功后】进行下面判断
			 //往数据库查找用户名是否存在
			 User userSJ= iUserService.finduser(username);
			 //再判断用户名是否一致
			 if(userSJ!=null){
				 //先取出来数据库中的密码再进行判断
				 String userSJuserpwd = userSJ.getUserPassword();
				 //用户名一致时再判断密码是否一致
				 if(!userpwd.equals(userSJuserpwd)){//不相等时
					//为空时就返回1001表示用户和密码为空【1001随便写】
					 result.put("code",1002); 
					 result.put("msg","密码错误"); 
					 
				 }else {
					 //为空时就返回200表示成功后跳出来
					 result.put("code",200); 
					 result.put("msg","登录成功"); 
				}
				 
			 }else{
				 //为空时就返回1001表示用户和密码为空【1001随便写】
				 result.put("code",1001); 
				 result.put("msg","用户错误"); 
			 }
			 
		 }else{
			 //为空时就返回1000表示用户和密码为空【1000随便写】
			 result.put("code",1000); 
			 result.put("msg","用户和密码为空");
		 }
		return result;
	}*/
	
	
	/*【第二种方式登录】  返回Map形式的登录
	 * @RequestMapping("loguser")
	@ResponseBody
	public Map loguser(User user,HttpServletRequest request){
		Map map=new HashMap<>();
		User userSJ=null;
		try {
			//接收前台参数
			String userName = user.getUserName();
			String userPassword = user.getUserPassword();
			//判断用户名和密码为空【其中一个为空 也不走下面判断了】
			if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(userPassword)){
				map.put("code",1000);
				map.put("msg","用户名和密码为空");
				return map;
			}
			 //当都不为空时走这个判断
			 //往数据库查找用户名是否存在
			userSJ= iUserService.finduser(userName);
			if(userSJ==null){
				map.put("code",1001);
				map.put("msg","用户名错误");
				return map;
			}
			//当用户名存在时再判断密码是否一致
			String userPwd = userSJ.getUserPassword();
			if(!userPassword.equals(userPwd)){
				map.put("code",1002);
				map.put("msg","密码错误");
				return map;
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("code",-1);
			map.put("msg","系统错误:"+e.getMessage());
			return map;
		}
		map.put("code",200);
		map.put("msg","登录成功");
		//放到Session中
		request.getSession().setAttribute("user",userSJ);
		return map;
    }*/
	
	
	
	/**
	 * loguser登录方法【第三种方式】(javaBeas形式的)  
	 * 【在ServerResponse类中 定义三个属性和生成set get 方法】
	 * 创建人：宋晓静       2208459187@qq.com    
	 * 创建时间：2019年1月11日 下午1:25:14    
	 * 修改人：宋晓静       2208459187@qq.com     
	 * 修改时间：2019年1月11日 下午1:25:14    
	 * 修改备注： 
	 * @param user
	 * @param request
	 * @return</pre>
	 */
	/*@RequestMapping("loguser")
	@ResponseBody
	//返回的是一个javaBeas
	public ServerResponse loguser(User user,HttpServletRequest request){
		ServerResponse serverResponse=new ServerResponse();
		User userSJ=null;
		try {
			//接收前台参数
			String userName = user.getUserName();
			String userPassword = user.getUserPassword();
			//判断用户名和密码为空【其中一个为空 也不走下面判断了】
			if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(userPassword)){
			 //通过对象的方式给属性赋值响应到前台
			 serverResponse.setCode(1000);
			 serverResponse.setMsg("用户名和密码为空");
			 return serverResponse;
			}
			 //当都不为空时走这个判断
			 //往数据库查找用户名是否存在
			userSJ= iUserService.finduser(userName);
			if(userSJ==null){
				 //通过对象的方式给属性赋值响应到前台
				 serverResponse.setCode(1001);
				 serverResponse.setMsg("用户名错误");
				 return serverResponse;
			}
			//当用户名存在时再判断密码是否一致
			String userPwd = userSJ.getUserPassword();
			if(!userPassword.equals(userPwd)){
				 //通过对象的方式给属性赋值响应到前台
				 serverResponse.setCode(1002);
				 serverResponse.setMsg("密码错误");
				 return serverResponse;
			}
		} catch (Exception e) {
			e.printStackTrace();
			 //通过对象的方式给属性赋值响应到前台
			 serverResponse.setCode(-1);
			 serverResponse.setMsg("系统错误:"+e.getMessage());
			 return serverResponse;
		}
		//通过对象的方式给属性赋值响应到前台
		 serverResponse.setCode(200);
		 serverResponse.setMsg("登录成功");
		//放到Session中
			request.getSession().setAttribute("user",userSJ);
		 return serverResponse;
    }*/
	
	/**
	 * 登录方法【第三种方式】(javaBeas形式的)优化一    
	 * 【只需要在ServerResponse类中 定义三个属性和生成 get方法   有参无参构造】
	 * 【通过有参构造方法赋值给前台响应值】
	 * 创建人：宋晓静       2208459187@qq.com  
	 * 创建时间：2019年1月11日 下午1:34:03    
	 * 修改人：宋晓静       2208459187@qq.com     
	 * 修改时间：2019年1月11日 下午1:34:03    
	 * 修改备注： 
	 * @param user
	 * @param request
	 * @return</pre>
	 */
	/*@RequestMapping("loguser")
	@ResponseBody
	//返回的是一个javaBeas
	public ServerResponse loguser(User user,HttpServletRequest request){
		ServerResponse serverResponse=new ServerResponse();
		User userSJ=null;
		try {
			//接收前台参数
			String userName = user.getUserName();
			String userPassword = user.getUserPassword();
			//判断用户名和密码为空【其中一个为空 也不走下面判断了】
			if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(userPassword)){
			 //通过有参构造方法赋值给前台响应值
			1. return new ServerResponse(1000,"用户名和密码为空",null);
			//通过类名.方法名给有参函数赋值
			2. return serverResponse.ServerResponse(1000,"用户名和密码为空",null);
			}
			 //当都不为空时走这个判断
			 //往数据库查找用户名是否存在
			userSJ= iUserService.finduser(userName);
			if(userSJ==null){
				 //通过有参构造方法赋值给前台响应值
				 return new ServerResponse(1001,"用户名错误",null);
			}
			//当用户名存在时再判断密码是否一致
			String userPwd = userSJ.getUserPassword();
			if(!userPassword.equals(userPwd)){
				 //通过有参构造方法赋值给前台响应值
				 return new ServerResponse(1002,"密码错误",null); 
			}
		} catch (Exception e) {
			e.printStackTrace();
			//通过有参构造方法赋值给前台响应值
			return new ServerResponse(-1,"系统错误:"+e.getMessage(),null); 
		}
		//放到Session中
		request.getSession().setAttribute("user",userSJ);
		//通过有参构造方法赋值给前台响应值
		return new ServerResponse(200,"登录成功",null);  
    }
	*/
	/**
	 * 登录方法【第三种方式】(javaBeas形式的)优化二
	 * 【只需要在ServerResponse类中 定义三个属性和生成 get方法   私有的有参无参构造】
	 * 【三个 public static ServerResponse  
	 * 【方法名(){}两个无参方法  一个有两个参数的方法在判断逻辑错误时的方法】
	 * 创建人：宋晓静       2208459187@qq.com    
	 * 创建时间：2019年1月11日 下午1:59:51    
	 * 修改人：宋晓静       2208459187@qq.com     
	 * 修改时间：2019年1月11日 下午1:59:51    
	 * 修改备注： 
	 * @param user
	 * @param request
	 * @return</pre>
	 */
	/*@RequestMapping("loguser")
	@ResponseBody
	//返回的是一个javaBeas
	public ServerResponse loguser(User user,HttpServletRequest request){
		User userSJ=null;
		try {
			//接收前台参数
			String userName = user.getUserName();
			String userPassword = user.getUserPassword();
			//判断用户名和密码为空【其中一个为空 也不走下面判断了】
			if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(userPassword)){
			 //通过调用类从而可以获得里面的方法
			 return ServerResponse.error(1000,"用户名和密码为空");
			}
			 //当都不为空时走这个判断
			 //往数据库查找用户名是否存在
			userSJ= iUserService.finduser(userName);
			if(userSJ==null){
				 //通过调用类从而可以获得里面的方法
				 return ServerResponse.error(1001,"用户名错误");
			}
			//当用户名存在时再判断密码是否一致
			String userPwd = userSJ.getUserPassword();
			if(!userPassword.equals(userPwd)){
				 //通过调用类从而可以获得里面的方法
				 return ServerResponse.error(1002,"密码错误");
			}
		} catch (Exception e) {
			e.printStackTrace();
			 //通过调用类从而可以获得里面的方法
			 return ServerResponse.error();
		}
		//放到Session中
		request.getSession().setAttribute("user",userSJ);
		//通过有参构造方法赋值给前台响应值
		return ServerResponse.success();
    }
	*/
	
	/**
	 * <pre>loguser(这里用一句话描述这个方法的作用)优化四  
	 * 【创建了一个枚举类】两个参数code,msg生成get方法和私有的有参和无参构造方法
	 *   通过这些名称给有参函数赋值USERNAME_USERPWD_ERROR(1000,"用户名和密码为空"),
	     USERNAME_ERROR(1001,"用户名错误"),
	     USERPED_ERROR(1002,"密码错误"),
	     ERROR(-1,"ERROR"),
	     SUCCESS(200,"OK");
	      【只需要在ServerResponse类中 定义三个属性和生成 get方法   私有的有参无参构造】
	 * 【三个 public static ServerResponse  
	 * 
	 * 
	 * 
	 * 创建人：宋晓静       2208459187@qq.com    
	 * 创建时间：2019年1月11日 下午3:36:20    
	 * 修改人：宋晓静       2208459187@qq.com     
	 * 修改时间：2019年1月11日 下午3:36:20    
	 * 修改备注： 
	 * @param user
	 * @param request
	 * @return</pre>
	 */
	/*@RequestMapping("loguser")
	 @ResponseBody
	//返回的是一个javaBeas
	public ServerResponse loguser(User user,HttpServletRequest request){
		User userSJ=null;
		try {
			//接收前台参数
			String userName = user.getUserName();
			String userPassword = user.getUserPassword();
			//判断用户名和密码为空【其中一个为空 也不走下面判断了】
			if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(userPassword)){
			 //通过调用类从而可以获得里面的方法
			// return ServerResponse.error(ResponseEnum.USERNAME_USERPWD_ERROR.getCode(),ResponseEnum.USERNAME_USERPWD_ERROR.getMsg());
			}
			 //当都不为空时走这个判断
			 //往数据库查找用户名是否存在
			userSJ= iUserService.finduser(userName);
			if(userSJ==null){
				 //通过调用类从而可以获得里面的方法
				 //return ServerResponse.error(ResponseEnum.USERNAME_ERROR.getCode(),ResponseEnum.USERNAME_ERROR.getMsg());
			}
			//当用户名存在时再判断密码是否一致
			String userPwd = userSJ.getUserPassword();
			if(!userPassword.equals(userPwd)){
				 //通过调用类从而可以获得里面的方法
				// return ServerResponse.error(ResponseEnum.USERPED_ERROR.getCode(),ResponseEnum.USERPED_ERROR.getMsg());
			}
		} catch (Exception e) {
			e.printStackTrace();
			 //通过调用类从而可以获得里面的方法
			 return ServerResponse.error();
		}
		//放到Session中
		request.getSession().setAttribute("user",userSJ);
		//通过有参构造方法赋值给前台响应值
		return ServerResponse.success();
    }*/
	
	
	
	@RequestMapping("loguser")
	@ResponseBody
	//自定义属性(使用户可以看到执行的信息)
	@LogAnnotation("用户登录")
	//返回的是一个javaBeas
	public ServerResponse loguser(User user,HttpServletRequest request){
		//全局变量必须赋初始值
		User userSJ=null;
		//写session
		String fh_id = CookieUtil.readCookie(request, "fh_id");
		try {
			//接收前台参数
			String userName = user.getUserName();
			String userPassword = user.getUserPassword();
			//验证码
			String imgCode = user.getImgCode();
			//判断用户名和密码为空【其中一个为空 也不走下面判断了】
			if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(userPassword) || StringUtils.isEmpty(imgCode)){
			 //通过调用类从而可以获得里面的方法
				return ServerResponse.error(ResponseEnum.USERNAME_USERPWD_ERROR);
			}

			//验证码从redis缓存中取出来
			//String sessionImgcode = (String) request.getSession().getAttribute(SystemConstants.IMAGE);
			String sessionImgcode = RedisUtils.get("code:" + fh_id);
			//判断从前台传的验证码是否和redis缓存中保持一样
			if (!imgCode.equals(sessionImgcode)){
				return ServerResponse.error(ResponseEnum.USERIMAGE_ERROR);
			}
			//当都不为空时走这个判断
			 //往数据库查找用户名是否存在
			userSJ= iUserService.finduser(userName);
			if(userSJ==null){
				 //通过调用类从而可以获得里面的方法
			    return ServerResponse.error(ResponseEnum.USERNAME_ERROR);
			}

            //数据库中状态是否是锁定状态
			int status = userSJ.getStatus();
			//判断当==1时是锁定状态
			if (status==SystemConstants.LOG_STATUS){
				return ServerResponse.error(ResponseEnum.USER_COUNT_ERROR);
			}
			//当用户名存在时再判断数据库中密码是否一致
			String userPwd = userSJ.getUserPassword();
			//从数据库中查询出来盐
			String salt = userSJ.getSalt();
			//登录时前台加了一次密,后台也加一次密
			String md5userPassword = MD5Util.MD5(userPassword+salt);
			//判断前台填的密码是否和数据库中的一样
			if(!md5userPassword.equals(userPwd)){
				//从数据库中取出来错误时间来进行判断
				Date errorloginTimes = userSJ.getErrorloginTimes();
				if (errorloginTimes==null){
					//当错误时间为空时,给错误次数重置为1,并且修改错误的登录时间为当前时间
					iUserService.updatErrorcount(userSJ.getId());
				}else {
					//记录本次登录时间
					Date currDate = new Date();
					//当前时间【转换为了年月日的时间类型】
					Date currDay = DateUtil.str2date(DateUtil.date2str(currDate, DateUtil.Y_M_D), DateUtil.Y_M_D);
					//System.out.println(currDay);
					//不为空时,判断错误登录的时间和当前登录时间是否是同一天
					Date errorDate = DateUtil.str2date(DateUtil.date2str(errorloginTimes, DateUtil.Y_M_D), DateUtil.Y_M_D);
					//System.out.println(errorDate);
					//当前时间在上次登录错误时间后面
					if (currDay.after(errorDate)){
						//【当今天登录2次都是错误的,隔一天时再次登录还是错误的就重置为1】
                        //不是同一天错误登录次数重置为1还有3次机会,并且更新错误时间不在同一天
						iUserService.updatErrorcount(userSJ.getId());
					}else {
						//在同一天内,错误登录次数等于2时,把状态重置为0
                       if (userSJ.getErrorcount()==SystemConstants.LOG_ERRORCOUNT-1){
                       	 //锁定该用户,把状态重置为0
						   iUserService.updateStatus(userSJ.getId());
					   }
						//上次登录时间在当前时间的前面,在同一天次数就+1,错误时间更新
						iUserService.updateCount(userSJ.getId());
					}
				}
				return ServerResponse.error(ResponseEnum.USERPED_ERROR);

		}

		} catch (Exception e) {
			e.printStackTrace();
			 //通过调用类从而可以获得里面的方法
			 return ServerResponse.error();
		}
		//把从数据库查出来的用户名和密码放到Session中
		//request.getSession().setAttribute(SystemConstants.USER_SESSION,userSJ);
		//删除redis中的验证码
		RedisUtils.del("code:"+fh_id);

		//安全
		userSJ.setUserPassword("");
		userSJ.setSalt("");
		Gson gson = new Gson();
		String userDBjson = gson.toJson(userSJ);
        RedisUtils.set("user:"+fh_id,userDBjson);
		RedisUtils.expire("user:"+fh_id,30*60);
		//当登录俩次都是错误的,隔一天后再次登录是对的,就把数据库中的次数重置为0
		iUserService.updatecount(userSJ.getId());

		//第二天登录次数为0
		if (Integer.valueOf(DateUtils.getYear())>userSJ.getLoginTimes().getYear()+1900 || Integer.valueOf(DateUtils.getMonth())>userSJ.getLoginTimes().getMonth()+1 || Integer.valueOf(DateUtils.getDay())>userSJ.getLoginTimes().getDate()  ) {
			userSJ.setFrequency(0);
		}
		//登录几次【LoginTimes这个字段的初始值为0每次都让它加1】
		userSJ.setFrequency(userSJ.getFrequency()+1);
		//上次登录的时间
		userSJ.setLoginTimeSci(userSJ.getLoginTimes());
		//修改登录次数
		iUserService.updateLoginTimes(userSJ);

		//这个也可以,传的是一个状态为1时登录成功并且放到Session
		//request.getSession().setAttribute("flag",1);
		//通过有参构造方法赋值给前台响应值
		return ServerResponse.success();
    }

    @RequestMapping("logout")
    //退出
	public String logout(HttpServletRequest request,HttpServletResponse response){
		String fh_id = CookieUtil.readCookie(request, "fh_id");
		if (StringUtils.isNotEmpty(fh_id)){
			//删除redis
			RedisUtils.del("user:"+fh_id);
			//删除Cookie
			CookieUtil.deleteCookie(response,"fh_id","admin.fh.com");
		}
		request.getSession().invalidate();
		return "redirect:/loguser.jsp";
	}


    //跳到新增页面
	@RequestMapping("toaddUser")
	public String toaddUser(){
		return "user/adduser";
	}
	/**
	 * 注册账号
	 */
	@RequestMapping("addUser")
	@ResponseBody
	//自定义属性(使用户可以看到执行的信息)
	@LogAnnotation("注册账号")
	public ServerResponse addUser(User user) {
		//按用户名查询,看注册的用户是否存在
		User userlist = iUserService.findlist(user);
		//判断不等于空数据库中就已经存在该用户
		if (userlist != null) {
			//直接return提示用户,该账号已存在
			return ServerResponse.error(ResponseEnum.USER_NAME_ERROR);
		}
		//等于null时数据库中没有存在该用户
		if (userlist == null) {
			//注册时给状态赋一个0,解锁状态
			user.setStatus(0);
			//判断用户
			iUserService.addUser(user);
		}
		return ServerResponse.success();
	}


	//查询用户列表
    @RequestMapping("findUserList")
    @ResponseBody
	public ServerResponse findUserList(User user){
		DataTableResult userList = iUserService.findUserList(user);
		return ServerResponse.success(userList);

	}

	//跳转到查询页面
	@RequestMapping("toQuery")
	public String toQuery(){
		return "user/userList";
	}
	//解锁
	@RequestMapping("userLockedStatus")
	public String userLockedStatus(Integer id){
		iUserService.updateUserLoginStatus(id);
		return "redirect:toQuery.jhtml";
	}

	//修改
	@RequestMapping("updateUser")
    @ResponseBody
	public ServerResponse updateUser(User user){
		iUserService.updateUser(user);
		return ServerResponse.success();
	}

	//批量删除
	@RequestMapping("deleteUser")
	@ResponseBody
	public ServerResponse deleteUser(String ids,HttpServletResponse response){
		iUserService.deleteUser(ids);
		return ServerResponse.success();
	}

	//批量用户修改部门
	@RequestMapping("updatebatch")
	@ResponseBody
	public ServerResponse updatebatch(@RequestParam("ids[]")List<Integer> ids,Integer deptid){
		iUserService.updatebatch(ids,deptid);
		return ServerResponse.success();
	}
    //部门的service层
	@Resource(name = "deptservice")
	private IDeptService deptService;



	//按部门导出用户
	@RequestMapping("exportDeptExcel")
	@ResponseBody
	public void exportDeptExcel(@RequestParam("childNode") List<Integer> childNode,
								HttpServletResponse response){
		//System.out.println(childNode+"---------------------------------------------");
		//查询有几个sheet页并且取出来名称
		List<Dept> listdept=deptService.deptExcelList(childNode);
		//创建workbook
		XSSFWorkbook wookBook=new XSSFWorkbook();
		//日期样式
		XSSFCellStyle dateStyle = wookBook.createCellStyle();
		dateStyle.setDataFormat(wookBook.createDataFormat().getFormat("yyyy-MM-dd hh:mm:ss"));
		//循环查出来的
		for (Dept dept : listdept) {
			//创建sheet
			XSSFSheet sheet = wookBook.createSheet(dept.getDeptName());
			//创建标题行并赋值
			XSSFRow titleRrow = sheet.createRow(0);
			XSSFCell Cell1 = titleRrow.createCell(0);
			Cell1.setCellValue("用户名");
			XSSFCell Cell2 = titleRrow.createCell(1);
			Cell2.setCellValue("真是姓名");
			XSSFCell Cell3 = titleRrow.createCell(2);
			Cell3.setCellValue("状态");
			XSSFCell Cell4 = titleRrow.createCell(3);
			Cell4.setCellValue("生日");
			XSSFCell Cell5 = titleRrow.createCell(4);
			Cell5.setCellValue("性别");
			XSSFCell Cell6 = titleRrow.createCell(5);
			Cell6.setCellValue("薪资");
			XSSFCell Cell7 = titleRrow.createCell(6);
			Cell7.setCellValue("所在部门");
			//根据父节点查看所有的子节点的id集合
			List<Integer>  deptIdList = deptService.findDeptByFatherId(dept.getId());
			//根据id集合查询所有的用户
			List<User> userList = iUserService.findUserByDeptId(deptIdList);
			//清空集合中的数据
			deptService.cleanList();
				for (int i=0;i<userList.size();i++) {
					User userInfo=userList.get(i);
					//创建内容行并赋值
					XSSFRow bodyRrow = sheet.createRow(i+1);

					XSSFCell userNameCell = bodyRrow.createCell(0);
					userNameCell.setCellValue(userInfo.getUserName());


					XSSFCell statusCell = bodyRrow.createCell(1);
					statusCell.setCellValue(userInfo.getUserRealName());

					XSSFCell realNameCell = bodyRrow.createCell(2);
					realNameCell.setCellValue(userInfo.getStatus());

					XSSFCell birthdayCell = bodyRrow.createCell(3);
					birthdayCell.setCellValue(userInfo.getBirthday());

					XSSFCell sexCell = bodyRrow.createCell(4);
					sexCell.setCellValue(userInfo.getSex());

					XSSFCell salaryCell = bodyRrow.createCell(5);
					salaryCell.setCellValue(userInfo.getSalary());

					XSSFCell deptNameCell = bodyRrow.createCell(6);
					deptNameCell.setCellValue(userInfo.getDeptName());
				}


		}
		//导出excel
		FileUtil.excelDownload(wookBook,response);

	}


	//删除用户信息
	@RequestMapping("deleteUserById")
	@ResponseBody
	public ServerResponse deleteUserById(Integer id){
		iUserService.deleteUserById(id);
		return ServerResponse.success();
	}

	//用户修改回显
	@RequestMapping("toupdateUser")
	@ResponseBody
	public ServerResponse toupdateUser(Integer id){
		User user=iUserService.toupdateUser(id);
		return ServerResponse.success(user);

	}



}


	
	


