package com.lexiu.app.user.dao;

import com.lexiu.app.user.entity.User;
import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;

/**
 * Created by root on 2017/9/21.
 */
@MyBatisDao
public interface UserDao extends CrudDao<User> {
}
