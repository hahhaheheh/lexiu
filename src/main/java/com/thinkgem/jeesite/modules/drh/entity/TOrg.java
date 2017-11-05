/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.drh.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 机构信息Entity
 * @author hl
 * @version 2017-10-25
 */
public class TOrg extends DataEntity<TOrg> implements Comparable<TOrg> {
	
	private static final long serialVersionUID = 1L;
	private String title;		// 名称
	private String tags;		// 标签
	private String province;		// 省
	private String city;		// 市
	private String distrinct;		// 区
	private String street;		// 街道
	private String streetnumber;		// 街道号
	private String longitude;		// 经度
	private String latitude;		// 纬度
	private String imageurl;		// 封面图
	private String startlevel;		// 星级
	private String phone;		// 联系方式
	private String cityid;		// 城市id
	private Double distance;//距离米
	
	private String description;//机构描述
	
	public TOrg() {
		super();
	}

	public TOrg(String id){
		super(id);
	}

	
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	@Length(min=0, max=100, message="名称长度必须介于 0 和 100 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=100, message="标签长度必须介于 0 和 100 之间")
	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}
	
	@Length(min=0, max=100, message="省长度必须介于 0 和 100 之间")
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
	@Length(min=0, max=100, message="市长度必须介于 0 和 100 之间")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	@Length(min=0, max=100, message="区长度必须介于 0 和 100 之间")
	public String getDistrinct() {
		return distrinct;
	}

	public void setDistrinct(String distrinct) {
		this.distrinct = distrinct;
	}
	
	@Length(min=0, max=100, message="街道长度必须介于 0 和 100 之间")
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}
	
	@Length(min=0, max=200, message="街道号长度必须介于 0 和 200 之间")
	public String getStreetnumber() {
		return streetnumber;
	}

	public void setStreetnumber(String streetnumber) {
		this.streetnumber = streetnumber;
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
	
	@Length(min=0, max=100, message="联系方式长度必须介于 0 和 100 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=0, max=100, message="城市id长度必须介于 0 和 100 之间")
	public String getCityid() {
		return cityid;
	}

	public void setCityid(String cityid) {
		this.cityid = cityid;
	}
	@Override
	public int compareTo(TOrg org) {
		return this.distance.compareTo(org.getDistance());
	}
}