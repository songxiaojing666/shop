package com.fh.shop.backend.po.post;


import com.fh.shop.backend.po.user.DataTablePage;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class Post extends DataTablePage implements Serializable {
    private static final long serialVersionUID = -8104403126207081430L;
    private Integer id;
    private String  userName;
    private String  password;
    private String  phone;
    private String  email;
    private String  province;//省
    private String  city;//市
    private String  country;//县
    private String  address;//省市县连接起来
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date birthday;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date minbirthday;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date maxbirthday;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date regTime;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date lastLoginTime;
    private String areaIds;

    public String getAreaIds() {
        return areaIds;
    }

    public void setAreaIds(String areaIds) {
        this.areaIds = areaIds;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}
