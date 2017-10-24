/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.drh.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.drh.entity.TOrg;
import com.thinkgem.jeesite.modules.drh.dao.TOrgDao;

/**
 * 机构信息Service
 * @author hl
 * @version 2017-10-24
 */
@Service
@Transactional(readOnly = true)
public class TOrgService extends CrudService<TOrgDao, TOrg> {

	public TOrg get(String id) {
		return super.get(id);
	}
	
	public List<TOrg> findList(TOrg tOrg) {
		return super.findList(tOrg);
	}
	
	public Page<TOrg> findPage(Page<TOrg> page, TOrg tOrg) {
		return super.findPage(page, tOrg);
	}
	
	@Transactional(readOnly = false)
	public void save(TOrg tOrg) {
		super.save(tOrg);
	}
	
	@Transactional(readOnly = false)
	public void delete(TOrg tOrg) {
		super.delete(tOrg);
	}
	
}