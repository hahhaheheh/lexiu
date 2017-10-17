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
import com.thinkgem.jeesite.modules.drh.entity.TOrg;
import com.thinkgem.jeesite.modules.drh.service.TOrgService;

/**
 * 机构信息Controller
 * @author hl
 * @version 2017-10-17
 */
@Controller
@RequestMapping(value = "${adminPath}/drh/tOrg")
public class TOrgController extends BaseController {

	@Autowired
	private TOrgService tOrgService;
	
	@ModelAttribute
	public TOrg get(@RequestParam(required=false) String id) {
		TOrg entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tOrgService.get(id);
		}
		if (entity == null){
			entity = new TOrg();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(TOrg tOrg, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TOrg> page = tOrgService.findPage(new Page<TOrg>(request, response), tOrg); 
		model.addAttribute("tOrg", tOrg);
		model.addAttribute("page", page);
		return "modules/drh/tOrgList";
	}

	@RequestMapping(value = "form")
	public String form(TOrg tOrg, Model model) {
		model.addAttribute("tOrg", tOrg);
		return "modules/drh/tOrgForm";
	}

	@RequestMapping(value = "save")
	public String save(TOrg tOrg, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tOrg)){
			return form(tOrg, model);
		}
		tOrgService.save(tOrg);
		addMessage(redirectAttributes, "保存机构信息成功");
		return "redirect:"+Global.getAdminPath()+"/drh/tOrg/?repage";
	}
	
	@RequestMapping(value = "delete")
	public String delete(TOrg tOrg, RedirectAttributes redirectAttributes) {
		tOrgService.delete(tOrg);
		addMessage(redirectAttributes, "删除机构信息成功");
		return "redirect:"+Global.getAdminPath()+"/drh/tOrg/?repage";
	}

}