/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.drh.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 用户信息Entity
 * @author hl
 * @version 2017-09-20
 */
public class TUser extends DataEntity<TUser> {
	
	private static final long serialVersionUID = 1L;
	private String sid;		// sid
	private String username;		// username
	private String password;		// password
	private String nickname;		// nickname
	private String signature;		// signature
	private String sex;		// sex
	private String age;		// age
	private String province;		// province
	private String city;		// city
	private String area;		// area
	private String address;		// address
	private String useravatar;		// useravatar头像
	private String professional;		// professional
	private String interest;		// interest
	private Long umoney;		// umoney
	private String idcard;		// idcard
	private String bankcard;		// bankcard
	private String source;//用户来源
	private String openId;
	private int praise;//点赞数
	private int showStatus;//主播开播状态 1开播 0未开播
	
	public TUser() {
		super();
	}

	public TUser(String id){
		super(id);
	}

	@Length(min=0, max=64, message="sid长度必须介于 0 和 64 之间")
	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}
	
	@Length(min=1, max=100, message="username长度必须介于 1 和 100 之间")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Length(min=1, max=100, message="password长度必须介于 1 和 100 之间")
	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Length(min=0, max=100, message="nickname长度必须介于 0 和 100 之间")
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	@Length(min=0, max=100, message="signature长度必须介于 0 和 100 之间")
	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}
	
	@Length(min=0, max=10, message="sex长度必须介于 0 和 10 之间")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Length(min=0, max=11, message="age长度必须介于 0 和 11 之间")
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	@Length(min=0, max=20, message="province长度必须介于 0 和 20 之间")
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
	@Length(min=0, max=20, message="city长度必须介于 0 和 20 之间")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	@Length(min=0, max=100, message="area长度必须介于 0 和 100 之间")
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	@Length(min=0, max=100, message="address长度必须介于 0 和 100 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=100, message="useravatar长度必须介于 0 和 100 之间")
	public String getUseravatar() {
		return useravatar;
	}

	public void setUseravatar(String useravatar) {
		this.useravatar = useravatar;
	}
	
	@Length(min=0, max=100, message="professional长度必须介于 0 和 100 之间")
	public String getProfessional() {
		return professional;
	}

	public void setProfessional(String professional) {
		this.professional = professional;
	}
	
	@Length(min=0, max=100, message="interest长度必须介于 0 和 100 之间")
	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}
	
	public Long getUmoney() {
		return umoney;
	}

	public void setUmoney(Long umoney) {
		this.umoney = umoney;
	}
	
	@Length(min=0, max=32, message="idcard长度必须介于 0 和 32 之间")
	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	
	@Length(min=0, max=32, message="bankcard长度必须介于 0 和 32 之间")
	public String getBankcard() {
		return bankcard;
	}

	public void setBankcard(String bankcard) {
		this.bankcard = bankcard;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public int getPraise() {
		return praise;
	}

	public void setPraise(int praise) {
		this.praise = praise;
	}

	public int getShowStatus() {
		return showStatus;
	}

	public void setShowStatus(int showStatus) {
		this.showStatus = showStatus;
	}
}