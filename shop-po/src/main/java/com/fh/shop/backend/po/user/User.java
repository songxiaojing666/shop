/** 
 * <pre>项目名称:shop-admin3 
 * 文件名称:User.java 
 * 包名:com.fh.shop.backend.po.user 
 * 创建日期:2019年1月7日下午3:53:27 
 * Copyright (c) 2019, 1071980754@qq.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.po.user;


import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/** 
 * <pre>项目名称：shop-admin3    
 * 类名称：User    
 * 类描述：  用户登录的实体类  
 * 创建人：宋晓静     2208459187  
 * 创建时间：2019年1月7日 下午3:53:27    
 * 修改人：宋晓静    2208459187  
 * 修改时间：2019年1月7日 下午3:53:27    
 * 修改备注：       
 * @version </pre>    
 */
public class User extends DataTablePage implements Serializable{
	private static final long serialVersionUID = 5966634492194465886L;
	private  Integer id;//ID
	private  String  userName;//用户名称
	private  String  userRealName;//真是姓名
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private  Date    birthday;//用户生日
	//最小生日
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date  minbirthday;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date  maxbirthday;

	private  Integer sex;//用户性别
	private  Double  salary;//薪资
	//最小薪资
	private Double  minsalary;
	private Double  maxsalary;
	//部门外键ID
    private int deptid;
    //部门名称
	private String deptName;
	private List<Integer> deptIdList;
	private String deptids;
	private  String  userPassword;
	//验证码字段数据库
	private String imgCode;
	//加盐字段
	private String  salt;
	//登录时间
	private Date loginTimes;
	//登录的次数
	private  int frequency;
	//上次登录的时间字段
	private  Date loginTimeSci;
	//错误登录时间
	private Date  errorloginTimes;
	//错误登录次数
	private int errorcount;
	//数据库中的状态
	private int status;

	private String headerPath;
	//老路径
	private String oldheaderPath;

	public String getOldheaderPath() {
		return oldheaderPath;
	}

	public void setOldheaderPath(String oldheaderPath) {
		this.oldheaderPath = oldheaderPath;
	}

	public String getHeaderPath() {
		return headerPath;
	}

	public void setHeaderPath(String headerPath) {
		this.headerPath = headerPath;
	}

	public String getDeptids() {
		return deptids;
	}

	public void setDeptids(String deptids) {
		this.deptids = deptids;
	}

	public List<Integer> getDeptIdList() {
		return deptIdList;
	}
	public void setDeptIdList(List<Integer> deptIdList) {
		this.deptIdList = deptIdList;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserRealName() {
		return userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Date getMinbirthday() {
		return minbirthday;
	}

	public void setMinbirthday(Date minbirthday) {
		this.minbirthday = minbirthday;
	}

	public Date getMaxbirthday() {
		return maxbirthday;
	}

	public void setMaxbirthday(Date maxbirthday) {
		this.maxbirthday = maxbirthday;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Double getMinsalary() {
		return minsalary;
	}

	public void setMinsalary(Double minsalary) {
		this.minsalary = minsalary;
	}

	public Double getMaxsalary() {
		return maxsalary;
	}

	public void setMaxsalary(Double maxsalary) {
		this.maxsalary = maxsalary;
	}

	public int getDeptid() {
		return deptid;
	}

	public void setDeptid(int deptid) {
		this.deptid = deptid;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getImgCode() {
		return imgCode;
	}

	public void setImgCode(String imgCode) {
		this.imgCode = imgCode;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Date getLoginTimes() {
		return loginTimes;
	}

	public void setLoginTimes(Date loginTimes) {
		this.loginTimes = loginTimes;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public Date getLoginTimeSci() {
		return loginTimeSci;
	}

	public void setLoginTimeSci(Date loginTimeSci) {
		this.loginTimeSci = loginTimeSci;
	}

	public Date getErrorloginTimes() {
		return errorloginTimes;
	}

	public void setErrorloginTimes(Date errorloginTimes) {
		this.errorloginTimes = errorloginTimes;
	}

	public int getErrorcount() {
		return errorcount;
	}

	public void setErrorcount(int errorcount) {
		this.errorcount = errorcount;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", userName='" + userName + '\'' +
				", userRealName='" + userRealName + '\'' +
				", birthday=" + birthday +
				", minbirthday=" + minbirthday +
				", maxbirthday=" + maxbirthday +
				", sex=" + sex +
				", salary=" + salary +
				", minsalary=" + minsalary +
				", maxsalary=" + maxsalary +
				", deptid=" + deptid +
				", deptName='" + deptName + '\'' +
				", userPassword='" + userPassword + '\'' +
				", imgCode='" + imgCode + '\'' +
				", salt='" + salt + '\'' +
				", loginTimes=" + loginTimes +
				", frequency=" + frequency +
				", loginTimeSci=" + loginTimeSci +
				", errorloginTimes=" + errorloginTimes +
				", errorcount=" + errorcount +
				", status=" + status +
				'}';
	}
}
