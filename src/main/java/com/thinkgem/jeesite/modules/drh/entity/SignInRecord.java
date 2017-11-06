package com.thinkgem.jeesite.modules.drh.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import java.util.Date;

/**
 * Created by Administrator on 2017/11/6.
 */
public class SignInRecord extends DataEntity<SignInRecord> {

    private static final long serialVersionUID = 1L;

    private String userId;

    private Date signDate;

    public SignInRecord() {
    }

    public SignInRecord(String id) {
        super(id);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }
}
