/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.drh.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 机构信息Entity
 * @author hl
 * @version 2017-10-17
 */
public class TOrg extends DataEntity<TOrg> {
	
	private static final long serialVersionUID = 1L;
	private String title;		// 名称
	private String tags;		// tags
	private String adress;		// adress
	private String longitude;		// 经度
	private String latitude;		// 纬度
	private String imageurl;		// 封面图
	private String startlevel;		// 星级
	
	public TOrg() {
		super();
	}

	public TOrg(String id){
		super(id);
	}

	@Length(min=0, max=100, message="名称长度必须介于 0 和 100 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=100, message="tags长度必须介于 0 和 100 之间")
	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}
	
	@Length(min=0, max=200, message="adress长度必须介于 0 和 200 之间")
	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}
	
	@Length(min=0, max=100, message="经度长度必须介于 0 和 100 之间")
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	@Length(min=0, max=100, message="纬度长度必须介于 0 和 100 之间")
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	@Length(min=0, max=100, message="封面图长度必须介于 0 和 100 之间")
	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	
	@Length(min=0, max=5, message="星级长度必须介于 0 和 5 之间")
	public String getStartlevel() {
		return startlevel;
	}

	public void setStartlevel(String startlevel) {
		this.startlevel = startlevel;
	}
	
}