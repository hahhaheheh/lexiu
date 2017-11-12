/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.drh.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.drh.entity.TUsercoupon;
import com.thinkgem.jeesite.modules.drh.dao.TUsercouponDao;

/**
 * usercouponService
 * @author hl
 * @version 2017-11-07
 */
@Service
@Transactional(readOnly = true)
public class TUsercouponService extends CrudService<TUsercouponDao, TUsercoupon> {

	public TUsercoupon get(String id) {
		return super.get(id);
	}
	
	public List<TUsercoupon> findList(TUsercoupon tUsercoupon) {
		return super.findList(tUsercoupon);
	}
	
	public Page<TUsercoupon> findPage(Page<TUsercoupon> page, TUsercoupon tUsercoupon) {
		return super.findPage(page, tUsercoupon);
	}
	
	@Transactional(readOnly = false)
	public void save(TUsercoupon tUsercoupon) {
		super.save(tUsercoupon);
	}
	
	@Transactional(readOnly = false)
	public void delete(TUsercoupon tUsercoupon) {
		super.delete(tUsercoupon);
	}
	
}