package com.thinkgem.jeesite.modules.drh.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

import java.util.Date;

/**
 * Created by Niexuyang on 2017/11/15.
 */
public class GiftRecord extends DataEntity<GiftRecord> {

    private static final long serialVersionUID = 1L;

    private String userId;//主播

    private String senderId;//送礼物的人

    private String giftId;//礼物

    private Date date;//时间

    public GiftRecord() {
    }

    public GiftRecord(String id) {
        super(id);
    }

    public GiftRecord(String userId, String senderId, String giftId, Date date) {
        this.userId = userId;
        this.senderId = senderId;
        this.giftId = giftId;
        this.date = date;
    }

    public GiftRecord(String id, String userId, String senderId, String giftId, Date date) {
        super(id);
        this.userId = userId;
        this.senderId = senderId;
        this.giftId = giftId;
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getGiftId() {
        return giftId;
    }

    public void setGiftId(String giftId) {
        this.giftId = giftId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
