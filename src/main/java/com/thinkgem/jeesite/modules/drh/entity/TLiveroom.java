/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.drh.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 直播间Entity
 * @author hl
 * @version 2017-11-07
 */
public class TLiveroom extends DataEntity<TLiveroom> {
	
	private static final long serialVersionUID = 1L;
	private String title;		// title房间名
	private String type;		// type直播类型
	private String isprivate;		// isprivate 是否加密
	private String roomkey; //加密key
	private int status;//直播状态 1开播 0未开播
	private String baseurl;		// baseurl房间url
	private String userid;		// userid用户信息
	private String username;		// username
	
	public TLiveroom() {
		super();
	}

	public TLiveroom(String id){
		super(id);
	}

	@Length(min=0, max=100, message="title长度必须介于 0 和 100 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=5, message="type长度必须介于 0 和 5 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=5, message="isprivate长度必须介于 0 和 5 之间")
	public String getIsprivate() {
		return isprivate;
	}

	public void setIsprivate(String isprivate) {
		this.isprivate = isprivate;
	}
	
	@Length(min=0, max=100, message="baseurl长度必须介于 0 和 100 之间")
	public String getBaseurl() {
		return baseurl;
	}

	public void setBaseurl(String baseurl) {
		this.baseurl = baseurl;
	}
	
	@Length(min=0, max=64, message="userid长度必须介于 0 和 64 之间")
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	@Length(min=0, max=100, message="username长度必须介于 0 和 100 之间")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRoomkey() {
		return roomkey;
	}

	public void setRoomkey(String roomkey) {
		this.roomkey = roomkey;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}