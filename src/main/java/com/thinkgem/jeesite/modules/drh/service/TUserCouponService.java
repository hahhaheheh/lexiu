package com.thinkgem.jeesite.modules.drh.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.drh.dao.TUserCouponDao;
import com.thinkgem.jeesite.modules.drh.dao.TUserDao;
import com.thinkgem.jeesite.modules.drh.entity.TUserCoupon;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Niexuyang on 2017/10/29.
 */
@Service
@Transactional(readOnly = true)
public class TUserCouponService extends CrudService<TUserCouponDao,TUserCoupon> {

    @Override
    public TUserCoupon get(String id) {
        return super.get(id);
    }

    @Override
    public TUserCoupon get(TUserCoupon entity) {
        return super.get(entity);
    }

    @Override
    public List<TUserCoupon> findList(TUserCoupon entity) {
        return super.findList(entity);
    }

    @Override
    public Page<TUserCoupon> findPage(Page<TUserCoupon> page, TUserCoupon entity) {
        return super.findPage(page, entity);
    }

    @Override
    public void save(TUserCoupon entity) {
        super.save(entity);
    }

    @Override
    public void delete(TUserCoupon entity) {
        super.delete(entity);
    }
}
