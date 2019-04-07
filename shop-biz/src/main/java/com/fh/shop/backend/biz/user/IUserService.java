/** 
 * <pre>项目名称:shop-admin3 
 * 文件名称:IUserService.java 
 * 包名:com.fh.shop.backend.biz.user 
 * 创建日期:2019年1月7日下午3:55:59 
 * Copyright (c) 2019, 1071980754@qq.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.biz.user;

import com.fh.shop.backend.common.DataTableResult;
import com.fh.shop.backend.po.user.User;

import java.util.List;

/** 
 * <pre>项目名称：shop-admin3    
 * 类名称：IUserService    
 * 类描述：    
 * 创建人：宋晓静     2208459187  
 * 创建时间：2019年1月7日 下午3:55:59    
 * 修改人：宋晓静    2208459187  
 * 修改时间：2019年1月7日 下午3:55:59    
 * 修改备注：       
 * @version </pre>    
 */
public interface IUserService {
	/** <pre>finduser(这里用一句话描述这个方法的作用)   
	 * 创建人：宋晓静       2208459187@qq.com    
	 * 创建时间：2019年1月7日 下午8:03:05    
	 * 修改人：宋晓静       2208459187@qq.com     
	 * 修改时间：2019年1月7日 下午8:03:05    
	 * 修改备注： 
	 * @param userName
	 * @return</pre>    
	 */
	public User finduser(String userName);
	//注册账号
    public void addUser(User user);
	//修改登录次数
    public void updateLoginTimes(User userSJ);
	//密码登录错误时错误登录次数加1
    public void updateerrorcount(Integer id);
	//当错误时间为空时,给错误次数重置为1,并且修改错误的登录时间
	public void updatErrorcount(Integer id);
	//按用户名查询,看注册的用户是否存在
	public User findlist(User user);
	//上次登录时间在当前时间的前面,在同一天次数就+1,错误时间更新
	public void updateCount(Integer id);
	//当登录俩次都是错误的,隔一天后再对登录就把数据库中的次数重置为0
	public void updatecount(Integer id);
  	//锁定该用户,把状态重置为0
    public void updateStatus(Integer id);
	//获取总条数
    //public Long findUserCount(User user);
	//获取分页的信息
	public DataTableResult findUserList(User user);
	//解锁
	public void updateUserLoginStatus(Integer id);
    //批量删除
    public void deleteUser(String ids);
	//修改
	public void updateUser(User user);
	//批量用户修改部门
    void updatebatch(List<Integer> ids, Integer deptid);
	//根据id集合查询所有的用户
    List<User> findUserByDeptId(List<Integer> deptIdList);
	//删除用户信息
	void deleteUserById(Integer id);
	//用户修改回显
    User toupdateUser(Integer id);
}
