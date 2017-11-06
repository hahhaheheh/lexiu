package com.thinkgem.jeesite.modules.drh.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * Created by Niexuyang on 2017/10/29.
 */
public class TUserCoupon extends DataEntity<TUserCoupon> {

    private static final long serialVersionUID = 1L;

    private String userId;

    private String couponId;

    public TUserCoupon() {
    }

    public TUserCoupon(String id) {
        super(id);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }
}

