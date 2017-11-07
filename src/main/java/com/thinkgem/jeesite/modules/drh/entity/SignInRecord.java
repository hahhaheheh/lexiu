package com.thinkgem.jeesite.modules.drh.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Administrator on 2017/11/6.
 */
public class SignInRecord extends DataEntity<SignInRecord> {

    private static final long serialVersionUID = 1L;

    private String userId;

    private Date signDate;

    private Date beginSignDate;

    private Date endSignDate;

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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message="beginSignDate不能为空")
    public Date getBeginSignDate() {
        return beginSignDate;
    }

    public void setBeginSignDate(Date beginSignDate) {
        this.beginSignDate = beginSignDate;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message="endSignDate不能为空")
    public Date getEndSignDate() {
        return endSignDate;
    }

    public void setEndSignDate(Date endSignDate) {
        this.endSignDate = endSignDate;
    }
}
