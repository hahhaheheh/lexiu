package com.thinkgem.jeesite.modules.drh.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Niexuyang on 2017/11/12.
 */
public class PaymentRecord extends DataEntity<PaymentRecord> {

    private static final long serialVersionUID = 1L;
    private String userId;
    private Date paymentDate;
    private BigDecimal rmb;
    private String status;
    private String opertion;
    private BigDecimal umoney;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getRmb() {
        return rmb;
    }

    public void setRmb(BigDecimal rmb) {
        this.rmb = rmb;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOpertion() {
        return opertion;
    }

    public void setOpertion(String opertion) {
        this.opertion = opertion;
    }

    public BigDecimal getUmoney() {
        return umoney;
    }

    public void setUmoney(BigDecimal umoney) {
        this.umoney = umoney;
    }
}
