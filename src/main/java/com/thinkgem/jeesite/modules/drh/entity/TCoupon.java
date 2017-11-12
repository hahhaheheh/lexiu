/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.drh.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * couponEntity
 * @author hl
 * @version 2017-11-07
 */
public class TCoupon extends DataEntity<TCoupon> {
	
	private static final long serialVersionUID = 1L;
	private String type;		// type
	private String name;		// name
	private String orgname;		// orgname
	private Date startdate;		// startdate
	private Date enddate;		// enddate
	private Double facevalue;		// facevalue
	private String status;		// status
	
	public TCoupon() {
		super();
	}

	public TCoupon(String id){
		super(id);
	}

	@Length(min=0, max=10, message="type长度必须介于 0 和 10 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=100, message="name长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=100, message="orgname长度必须介于 0 和 100 之间")
	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	
	public Double getFacevalue() {
		return facevalue;
	}

	public void setFacevalue(Double facevalue) {
		this.facevalue = facevalue;
	}
	
	@Length(min=0, max=10, message="status长度必须介于 0 和 10 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}