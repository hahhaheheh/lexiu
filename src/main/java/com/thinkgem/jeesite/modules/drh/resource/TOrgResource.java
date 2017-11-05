package com.thinkgem.jeesite.modules.drh.resource;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.modules.drh.ResultModel;
import com.thinkgem.jeesite.modules.drh.entity.TMyattentionorfans;
import com.thinkgem.jeesite.modules.drh.entity.TOrg;
import com.thinkgem.jeesite.modules.drh.entity.TUser;
import com.thinkgem.jeesite.modules.drh.service.TOrgService;
import com.thinkgem.jeesite.modules.drh.util.LocationUtil;

@Controller
@RequestMapping("/org")
public class TOrgResource {
	@Autowired
	private TOrgService tOrgService;
	 @RequestMapping("/around/list")
	 @ResponseBody
	 public ResultModel aroundOrg(String cityId, String lng,String lat){
		 TOrg tOrg=new TOrg();
		 tOrg.setCityid(cityId);
		 List<TOrg> orgList=tOrgService.findList(tOrg);
		 //计算距离
		 for (TOrg temp : orgList) {
			temp.setDistance(LocationUtil.getDistance(Double.parseDouble(lat), Double.parseDouble(lng), Double.parseDouble(temp.getLatitude()), Double.parseDouble(temp.getLongitude())));
		}
		 //排序
		 Collections.sort(orgList);
		 ResultModel rm=new ResultModel(0,"success",new LinkedHashMap());
		 int i=0;
     	for (TOrg org : orgList) {
     		i++;
     		rm.put(""+i, org);
			}
     	return rm;
	 }
}
