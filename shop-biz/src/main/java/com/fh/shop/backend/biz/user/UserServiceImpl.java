/** 
 * <pre>项目名称:shop-admin3 
 * 文件名称:UserServiceImpl.java 
 * 包名:com.fh.shop.backend.biz.user 
 * 创建日期:2019年1月7日下午3:56:30 
 * Copyright (c) 2019, 1071980754@qq.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.biz.user;

import com.fh.shop.backend.common.DataTableResult;
import com.fh.shop.backend.util.COSUtil;
import com.fh.shop.backend.util.DateUtils;
import com.fh.shop.backend.util.MD5Util;
import com.fh.shop.backend.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fh.shop.backend.mapper.user.IUserMapper;
import com.fh.shop.backend.po.user.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/** 
 * <pre>项目名称：shop-admin3    
 * 类名称：UserServiceImpl    
 * 类描述：    
 * 创建人：宋晓静     2208459187  
 * 创建时间：2019年1月7日 下午3:56:30    
 * 修改人：宋晓静    2208459187  
 * 修改时间：2019年1月7日 下午3:56:30    
 * 修改备注：       
 * @version </pre>    
 */
@Service("user")
public class UserServiceImpl implements IUserService{
	//注入mapper层
	@Autowired
	private IUserMapper iUserMapper;
	/*登录  
	 */
	@Override
	public User finduser(String userName) {
		User finduser = iUserMapper.finduser(userName);
		return finduser;
	}
    //注册账号
	@Override
	public void addUser(User user) {
		Date date = new Date();
		//登录时间
		user.setLoginTimes(date);
		//加盐【盐就相当于UUID】
		String salt = UUID.randomUUID().toString();
		System.out.println(salt);
		//把盐赋值给对象中
		user.setSalt(salt);
		//后台再次加密
		String md5 = MD5Util.MD5(user.getUserPassword() + salt);
		System.out.println(md5);
		user.setUserPassword(md5);
		user.setFrequency(0);
		iUserMapper.addUser(user);
	}
	//修改登录次数
	@Override
	public void updateLoginTimes(User userSJ) {
		userSJ.setLoginTimes(DateUtils.getDateNow());
		iUserMapper.updateLoginTimes(userSJ);

	}
	//密码登录错误时错误登录次数加1
	@Override
	public void updateerrorcount(Integer id) {
		iUserMapper.updateerrorcount(id);
	}

	//当错误时间为空时,给错误次数重置为1,并且修改错误的登录时间
	@Override
	public void updatErrorcount(Integer id) {
		iUserMapper.updatErrorcount(id,new Date());
	}
	//按用户名查询,看注册的用户是否存在
	@Override
	public User findlist(User user) {
		return iUserMapper.findlist(user);
	}
	//上次登录时间在当前时间的前面,在同一天次数就+1,错误时间更新
	@Override
	public void updateCount(Integer id) {
		iUserMapper.updateCount(id,new Date());
	}
	//当登录俩次都是错误的,隔一天后再对登录就把数据库中的次数重置为0
	@Override
	public void updatecount(Integer id) {
		iUserMapper.updatecount(id);
	}
	//锁定该用户,把状态重置为0
	@Override
	public void updateStatus(Integer id) {
		iUserMapper.updateStatus(id);
	}
	//获取总条数
	//@Override
	/*public Long findUserCount(User user) {
		return iUserMapper.findUserCount(user);
	}*/
	//获取分页的信息
	@Override
	public DataTableResult findUserList(User user) {
		//填充排序信息

		//转换部门列表
		String deptids = user.getDeptids();
		if (StringUtils.isNotEmpty(deptids)){

			String[] deptidsArr = deptids.split(",");
			List<Integer> deptList = new ArrayList<Integer>();
			//for循环
			for (String s : deptidsArr) {
				deptList.add(Integer.parseInt(s));
			}
			user.setDeptIdList(deptList);

		}

		//获取总条数
		Long totalCount=iUserMapper.findUserCount(user);
		//获取分页列表
		List<User> userList=iUserMapper.findUserList(user);
		//PO转VO
		List<UserVo> userVoList=new ArrayList<UserVo>();
		for (User user1 : userList) {
			UserVo vo = new UserVo();
			vo.setId(user1.getId());
			vo.setUserName(user1.getUserName());
			vo.setUserRealName(user1.getUserRealName());
			vo.setBirthday(user1.getBirthday());
			vo.setSex(user1.getSex());
			vo.setSalary(user1.getSalary());
			vo.setStatus(user1.getStatus());
			vo.setDeptName(user1.getDeptName());
			vo.setDeptid(user1.getDeptid());
			vo.setHeaderPath(user1.getHeaderPath());
			userVoList.add(vo);
		}
		//组装结果
		DataTableResult result = DataTableResult.build(user.getDraw(), totalCount, totalCount, userVoList);


		return result;
	}
	//解锁
	@Override
	public void updateUserLoginStatus(Integer id) {
		iUserMapper.updateUserLoginStatus(id);
	}

	//批量删除
	@Override
	public void deleteUser(String ids) {
		List<Integer> list = new ArrayList<Integer>();
		String[] split = ids.split(",");
		for (String id : split) {
			list.add(Integer.parseInt(id));
		}
		iUserMapper.deleteUser(list);
	}
	//修改
	@Override
	public void updateUser(User user) {
     //判断是否上传了新图片
		String headerPath = user.getHeaderPath();
		String oldheaderPath = user.getOldheaderPath();
		if (StringUtils.isNotEmpty(headerPath)){
			//上传了新图片把cos上的老图片删除掉
			COSUtil.deleteFile(oldheaderPath);

		}else {
			user.setHeaderPath(oldheaderPath);
		}

		iUserMapper.updateUser(user);
	}


	//批量用户修改部门
	@Override
	public void updatebatch(List<Integer> ids, Integer deptid) {
		iUserMapper.updatebatch(ids,deptid);
	}
	//根据id集合查询所有的用户
	@Override
	public List<User> findUserByDeptId(List<Integer> deptIdList) {
		return iUserMapper.findUserByDeptId(deptIdList);
	}

	//删除用户信息
	@Override
	public void deleteUserById(Integer id) {
		//查询用户对应的头像
		User user=iUserMapper.findUserByImage(id);
		//删除头像
		String headerPath = user.getHeaderPath();
		//删除云服务器上的图片
		COSUtil.deleteFile(headerPath);
		//删除用户
		iUserMapper.deleteUserById(id);

	}
	//用户修改回显
	@Override
	public User toupdateUser(Integer id) {
		return iUserMapper.toupdateUser(id);
	}


}
