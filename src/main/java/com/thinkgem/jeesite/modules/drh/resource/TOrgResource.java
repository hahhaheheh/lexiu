package com.thinkgem.jeesite.modules.drh.resource;

import java.io.File;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.thinkgem.jeesite.modules.drh.ResultModel;
import com.thinkgem.jeesite.modules.drh.entity.TMyattentionorfans;
import com.thinkgem.jeesite.modules.drh.entity.TOrg;
import com.thinkgem.jeesite.modules.drh.entity.TOrgComment;
import com.thinkgem.jeesite.modules.drh.entity.TUser;
import com.thinkgem.jeesite.modules.drh.service.TOrgCommentService;
import com.thinkgem.jeesite.modules.drh.service.TOrgService;
import com.thinkgem.jeesite.modules.drh.util.LocationUtil;
import com.thinkgem.jeesite.modules.drh.util.UploadHelper;

@Controller
@RequestMapping("/org")
public class TOrgResource {
	@Autowired
	private TOrgService tOrgService;
	@Autowired
	private TOrgCommentService tOrgCommentService;
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
	 @RequestMapping("/comment/save")
	 @ResponseBody
	 public ResultModel save(TOrgComment tOrgComment,HttpServletRequest request,HttpServletResponse response){
		 List<MultipartFile> multipartFiles = UploadHelper.getFileSet(request, 1024 * 20, null);
		 String path = "D:" + File.separator+"commentImage";
		 String filePath ="";
		 for (MultipartFile multipartFile : multipartFiles) {
			 try {
				 filePath=UploadHelper.uploadFile(multipartFile, path)+",";
				 System.out.println(filePath);
			 } catch (Exception e) {
			e.printStackTrace();
			 }
			 }
		 tOrgComment.setImageurl(path);
		 tOrgCommentService.save(tOrgComment);
		 ResultModel rm=new ResultModel(0,"success",new LinkedHashMap());
     	return rm;
	 }
	 @RequestMapping("/comment/list")
	 @ResponseBody
	 public ResultModel list(String orgid,HttpServletRequest request,HttpServletResponse response){
		 TOrgComment tOrgComment=new TOrgComment();
		 ResultModel rm=new ResultModel(0,"success",new LinkedHashMap());
		 tOrgComment.setOrgid(orgid);
		List<TOrgComment> commentList= tOrgCommentService.findList(tOrgComment);
		 int i=0;
	     	for (TOrgComment comment : commentList) {
	     		i++;
	     		rm.put(""+i, comment);
				}
		
     	return rm;
	 }
}
