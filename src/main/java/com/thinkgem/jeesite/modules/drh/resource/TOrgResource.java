package com.thinkgem.jeesite.modules.drh.resource;

import java.io.File;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.JedisUtils;
import com.thinkgem.jeesite.modules.drh.ResultModel;
import com.thinkgem.jeesite.modules.drh.entity.TMyattentionorfans;
import com.thinkgem.jeesite.modules.drh.entity.TMycollect;
import com.thinkgem.jeesite.modules.drh.entity.TOrg;
import com.thinkgem.jeesite.modules.drh.entity.TOrgComment;
import com.thinkgem.jeesite.modules.drh.entity.TUser;
import com.thinkgem.jeesite.modules.drh.service.TMycollectService;
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
	@Autowired
	private TMycollectService tMycollectService;
	 @RequestMapping("/around/list")
	 @ResponseBody
	 public ResultModel aroundOrg(String token,TOrg tOrg){
		 TUser user = (TUser) JedisUtils.getObject(token);
	        if (user==null){
	            return new ResultModel(1000,"用户未登录",null);
	        }else {
	        	 ResultModel rm=new ResultModel(0,"success",new LinkedHashMap());
	    		 List<TOrg> orgList=tOrgService.findList(tOrg);
	    		 //计算距离
	    		 for (TOrg temp : orgList) {
	    			temp.setDistance(LocationUtil.getDistance(Double.parseDouble(tOrg.getLatitude()), Double.parseDouble(tOrg.getLongitude()), Double.parseDouble(temp.getLatitude()), Double.parseDouble(temp.getLongitude())));
	    		}
	    		 //排序
	    		 Collections.sort(orgList);
	         	return rm.put("orgList",orgList);
	        }
		
	 }
	 @RequestMapping("/comment/save")
	 @ResponseBody
	 public ResultModel save(String token,TOrgComment tOrgComment,HttpServletRequest request,HttpServletResponse response){
		 TUser user = (TUser) JedisUtils.getObject(token);
	        if (user==null){
	            return new ResultModel(1000,"用户未登录",null);
	        }else {
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
	    		 tOrgComment.setUserid(user.getId());
	    		 tOrgCommentService.save(tOrgComment);
	    		 ResultModel rm=new ResultModel(0,"success",new LinkedHashMap());
	         	return rm;
	        }
		
	 }
	 @RequestMapping("/comment/list")
	 @ResponseBody
	 public ResultModel list(String token,String orgid,HttpServletRequest request,HttpServletResponse response){
		 TUser user = (TUser) JedisUtils.getObject(token);
	        if (user==null){
	            return new ResultModel(1000,"用户未登录",null);
	        }else {
	        	TOrgComment tOrgComment=new TOrgComment();
		   		 ResultModel rm=new ResultModel(0,"success",new LinkedHashMap());
		   		 tOrgComment.setOrgid(orgid);
		   		List<TOrgComment> commentList= tOrgCommentService.findList(tOrgComment);
	        	return rm.put("commentList", commentList);
	        }
		 
	 }
	 @RequestMapping("/around/collection")
	 @ResponseBody
		public ResultModel collection(String token,TMycollect tMycollect, Model model, RedirectAttributes redirectAttributes) {
		 TUser user = (TUser) JedisUtils.getObject(token);
	        if (user==null){
	            return new ResultModel(1000,"用户未登录",null);
	        }else {
	        	tMycollect.setStatus("0");
	   		 tMycollectService.save(tMycollect);
	   			ResultModel rm=new ResultModel(0,"success",new LinkedHashMap());
	   			return rm;
	        }
		 
		}
	 @RequestMapping("/around/canclecollection")
	 @ResponseBody
		public ResultModel canclecollection(String token,TMycollect tMycollect, Model model, RedirectAttributes redirectAttributes) {
		 TUser user = (TUser) JedisUtils.getObject(token);
	        if (user==null){
	            return new ResultModel(1000,"用户未登录",null);
	        }else {
	        	tMycollect.setStatus("0");	
	   		 tMycollectService.delete(tMycollect);;
	   			ResultModel rm=new ResultModel(0,"success",new LinkedHashMap());
	   			return rm;
	        }
		 
		}
	 @RequestMapping("/around/aroundlist")
	 @ResponseBody
		public ResultModel collectionList(String token, Model model, RedirectAttributes redirectAttributes) {
		 TUser user = (TUser) JedisUtils.getObject(token);
	        if (user==null){
	            return new ResultModel(1000,"用户未登录",null);
	        }else {
	        	 TMycollect tMycollect=new TMycollect();
	        	 tMycollect.setStatus("0");
	        	 tMycollect.setUserid(user.getId());
	    		 List<TMycollect> tMycollectList=tMycollectService.findList(tMycollect);
	    			ResultModel rm=new ResultModel(0,"success",new LinkedHashMap()).put("collectionlist", tMycollectList);
	    			return rm;
	        }
		
		}
}
