package com.thinkgem.jeesite.modules.drh.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.drh.entity.SignInRecord;

/**
 * Created by Administrator on 2017/11/6.
 */
@MyBatisDao
public interface SignInRecordDao extends CrudDao<SignInRecord> {
}
