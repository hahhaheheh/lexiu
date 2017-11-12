/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.drh.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * usercouponEntity
 * @author hl
 * @version 2017-11-07
 */
public class TUsercoupon extends DataEntity<TUsercoupon> {
	
	private static final long serialVersionUID = 1L;
	private String userid;		// userid
	private String couponid;		// couponid
	
	public TUsercoupon() {
		super();
	}

	public TUsercoupon(String id){
		super(id);
	}

	@Length(min=1, max=64, message="userid长度必须介于 1 和 64 之间")
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	@Length(min=0, max=64, message="couponid长度必须介于 0 和 64 之间")
	public String getCouponid() {
		return couponid;
	}

	public void setCouponid(String couponid) {
		this.couponid = couponid;
	}
	
}