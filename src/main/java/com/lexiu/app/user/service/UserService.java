package com.lexiu.app.user.service;

import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.modules.sys.dao.UserDao;

/**
 * Created by root on 2017/9/21.
 */
@Service
@Transactional(readOnly = true)
public class UserService extends BaseService {

    @Autowired
    private UserDao userDao;
}
