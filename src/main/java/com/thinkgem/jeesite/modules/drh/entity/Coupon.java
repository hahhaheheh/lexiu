package com.thinkgem.jeesite.modules.drh.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Niexuyang on 2017/10/29.
 */
public class Coupon  extends DataEntity<Coupon> {

    private static final long serialVersionUID = 1L;

    private String type;

    private String name;

    private String orgName;

    private Date startDate;

    private Date endDate;

    private BigDecimal faceValue;

    private String status;

    public Coupon() {
    }

    public Coupon(String id) {
        super(id);
    }

    @Length(min=0, max=20, message="类型必须介于 0 和 20 之间")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Length(min=0, max=80, message="类型必须介于 0 和 80 之间")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Length(min=0, max=120, message="类型必须介于 0 和 120 之间")
    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message="startDate不能为空")
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message="endDate不能为空")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(BigDecimal faceValue) {
        this.faceValue = faceValue;
    }
    @Length(min=0, max=20, message="类型必须介于 0 和 20 之间")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

