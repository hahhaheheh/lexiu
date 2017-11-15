package com.thinkgem.jeesite.modules.drh.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * Created by Niexuyang on 2017/11/14.
 */
public class Gift extends DataEntity<Gift> {

    private static final long serialVersionUID = 1L;

    private String name;//礼物名称

    private Long umoney;//u币

    public Gift() {
    }

    public Gift(String id) {
        super(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUmoney() {
        return umoney;
    }

    public void setUmoney(Long umoney) {
        this.umoney = umoney;
    }
}
