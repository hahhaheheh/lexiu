package com.lexiu.app.user.entity;

import com.thinkgem.jeesite.common.persistence.BaseEntity;

import java.math.BigDecimal;

/**
 * Created by root on 2017/9/18.
 */
public class User extends BaseEntity<User> {

    private static final long serialVersionUID = 1L;

    //用户前端展示唯一ID
    private String sId;

    private String userName;

    private String password;

    private String nickName;
    //签名
    private String signature;

    private String sex;

    private int age;

    private String province;

    private String city;

    private String area;

    private String address;
    //用户头像
    private String userAvatar;

    private String professional;

    private String interest;

    private BigDecimal uMoney;

    private String idcard;

    private String bankcard;


    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getProfessional() {
        return professional;
    }

    public void setProfessional(String professional) {
        this.professional = professional;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public BigDecimal getuMoney() {
        return uMoney;
    }

    public void setuMoney(BigDecimal uMoney) {
        this.uMoney = uMoney;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getBankcard() {
        return bankcard;
    }

    public void setBankcard(String bankcard) {
        this.bankcard = bankcard;
    }

    @Override
    public void preInsert() {

    }

    @Override
    public void preUpdate() {

    }
}
