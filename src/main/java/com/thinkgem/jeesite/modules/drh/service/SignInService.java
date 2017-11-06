package com.thinkgem.jeesite.modules.drh.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.drh.dao.SignInRecordDao;
import com.thinkgem.jeesite.modules.drh.entity.SignInRecord;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2017/11/6.
 */
@Service
@Transactional(readOnly = true)
public class SignInService extends CrudService<SignInRecordDao,SignInRecord> {

    @Override
    public SignInRecord get(String id) {
        return super.get(id);
    }

    @Override
    public SignInRecord get(SignInRecord entity) {
        return super.get(entity);
    }

    @Override
    public List<SignInRecord> findList(SignInRecord entity) {
        return super.findList(entity);
    }

    @Override
    public Page<SignInRecord> findPage(Page<SignInRecord> page, SignInRecord entity) {
        return super.findPage(page, entity);
    }

    @Override
    public void save(SignInRecord entity) {
        super.save(entity);
    }

    @Override
    public void delete(SignInRecord entity) {
        super.delete(entity);
    }
}
