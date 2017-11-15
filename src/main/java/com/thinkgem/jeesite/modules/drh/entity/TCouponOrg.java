package com.thinkgem.jeesite.modules.drh.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import java.util.Date;

/**
 * Created by Niexuyang on 2017/11/15.
 */
public class TCouponOrg extends DataEntity<TCouponOrg>{

    private static final long serialVersionUID = 1L;

    private String couponId;
    private String orgId;
    private Date startdate;		// startdate
    private Date enddate;		// enddate

    public TCouponOrg() {
    }

    public TCouponOrg(String id) {
        super(id);
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }



    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }
}
