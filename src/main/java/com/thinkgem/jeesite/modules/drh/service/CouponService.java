package com.thinkgem.jeesite.modules.drh.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.drh.dao.CouponDao;
import com.thinkgem.jeesite.modules.drh.entity.Coupon;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Niexuyang on 2017/10/29.
 */
@Service
@Transactional(readOnly = true)
public class CouponService extends CrudService<CouponDao,Coupon> {

    @Override
    public Coupon get(String id) {
        return super.get(id);
    }

    @Override
    public Coupon get(Coupon entity) {
        return super.get(entity);
    }

    @Override
    public List<Coupon> findList(Coupon entity) {
        return super.findList(entity);
    }

    @Override
    public Page<Coupon> findPage(Page<Coupon> page, Coupon entity) {
        return super.findPage(page, entity);
    }
    @Transactional(readOnly = false)
    @Override
    public void save(Coupon entity) {
        super.save(entity);
    }
    @Transactional(readOnly = false)
    @Override
    public void delete(Coupon entity) {
        super.delete(entity);
    }
}
