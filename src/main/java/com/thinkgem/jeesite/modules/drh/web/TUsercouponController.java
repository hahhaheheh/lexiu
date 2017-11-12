/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.drh.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.drh.entity.TUsercoupon;
import com.thinkgem.jeesite.modules.drh.service.TUsercouponService;

/**
 * usercouponController
 * @author hl
 * @version 2017-11-07
 */
@Controller
@RequestMapping(value = "${adminPath}/drh/tUsercoupon")
public class TUsercouponController extends BaseController {

	@Autowired
	private TUsercouponService tUsercouponService;
	
	@ModelAttribute
	public TUsercoupon get(@RequestParam(required=false) String id) {
		TUsercoupon entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tUsercouponService.get(id);
		}
		if (entity == null){
			entity = new TUsercoupon();
		}
		return entity;
	}
	
	@RequiresPermissions("drh:tUsercoupon:view")
	@RequestMapping(value = {"list", ""})
	public String list(TUsercoupon tUsercoupon, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TUsercoupon> page = tUsercouponService.findPage(new Page<TUsercoupon>(request, response), tUsercoupon); 
		model.addAttribute("page", page);
		return "modules/drh/tUsercouponList";
	}

	@RequiresPermissions("drh:tUsercoupon:view")
	@RequestMapping(value = "form")
	public String form(TUsercoupon tUsercoupon, Model model) {
		model.addAttribute("tUsercoupon", tUsercoupon);
		return "modules/drh/tUsercouponForm";
	}

	@RequiresPermissions("drh:tUsercoupon:edit")
	@RequestMapping(value = "save")
	public String save(TUsercoupon tUsercoupon, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tUsercoupon)){
			return form(tUsercoupon, model);
		}
		tUsercouponService.save(tUsercoupon);
		addMessage(redirectAttributes, "保存usercoupon成功");
		return "redirect:"+Global.getAdminPath()+"/drh/tUsercoupon/?repage";
	}
	
	@RequiresPermissions("drh:tUsercoupon:edit")
	@RequestMapping(value = "delete")
	public String delete(TUsercoupon tUsercoupon, RedirectAttributes redirectAttributes) {
		tUsercouponService.delete(tUsercoupon);
		addMessage(redirectAttributes, "删除usercoupon成功");
		return "redirect:"+Global.getAdminPath()+"/drh/tUsercoupon/?repage";
	}

}