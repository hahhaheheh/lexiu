package com.thinkgem.jeesite.modules.drh.resource;

import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.utils.JedisUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.drh.ResultModel;
import com.thinkgem.jeesite.modules.drh.entity.TMyattentionorfans;
import com.thinkgem.jeesite.modules.drh.entity.TUser;
import com.thinkgem.jeesite.modules.drh.service.TMyattentionorfansService;
import com.thinkgem.jeesite.modules.drh.service.TUserService;

@Controller
@RequestMapping("/personal")
public class PersonalCenterResource {
	@Autowired
	private TMyattentionorfansService tMyattentionorfansService;
	@Autowired
	private TUserService tUserService;
	/**
	 * 查询粉丝个数
	 * @param token 
	 * @return
	 */
	 @RequestMapping("/fans/count")
	 @ResponseBody
	 public ResultModel fansCount(String token){
		 TUser user = (TUser) JedisUtils.getObject(token);
	        if (user==null){
	            return new ResultModel(1000,"用户未登录",null);
	        }else {
	        	String count=tMyattentionorfansService.getFansCount(user.getId());
	        	return new ResultModel(0,"success",new LinkedHashMap()).put("count",count);
	        }
	 }
	 /**
	  * 用户粉丝列表
	  * @param token
	  * @return
	  */
	 @RequestMapping("/fans/list")
	 @ResponseBody
	 public ResultModel fansList( String token){
		 TUser user = (TUser) JedisUtils.getObject(token);
	        if (user==null){
	            return new ResultModel(1000,"用户未登录",null);
	        }else {
	        	ResultModel rm=new ResultModel(0,"success",new LinkedHashMap());
	        	TMyattentionorfans tMyattentionorfans=new TMyattentionorfans();
	        	tMyattentionorfans.setAttentionid(user.getId());
	        	List<TMyattentionorfans> tMyattentionorfansList=tMyattentionorfansService.findList(tMyattentionorfans);
	        	int i=0;
	        	for (TMyattentionorfans tMyattentionorfansTemp : tMyattentionorfansList) {
	        		i++;
	        		TUser t=tUserService.get(tMyattentionorfansTemp.getFansid());
	        		rm.put("user"+i, t);
				}
	        	return rm;
	        }
	 }
	 /**
	  * 查询我关注个数
	  * @param token
	  * @return
	  */
	 @RequestMapping("/attention/count")
	 @ResponseBody
	 public ResultModel attentionCount( String token){
		 TUser user = (TUser) JedisUtils.getObject(token);
	        if (user==null){
	            return new ResultModel(1000,"用户未登录",null);
	        }else {
	        	String count=tMyattentionorfansService.getAttentionCount(user.getId());
	        	return new ResultModel(0,"success",new LinkedHashMap()).put("code",count);
	        }
	 }
	 /**
	  * 查询关注着列表
	  * @param token
	  * @return
	  */
	 @RequestMapping("/attention/list")
	 @ResponseBody
	 public ResultModel attentionList( String token){
		 TUser user = (TUser) JedisUtils.getObject(token);
	        if (user==null){
	            return new ResultModel(1000,"用户未登录",null);
	        }else {
	        	ResultModel rm=new ResultModel(0,"success",new LinkedHashMap());
	        	TMyattentionorfans tMyattentionorfans=new TMyattentionorfans();
	        	tMyattentionorfans.setFansid(user.getId());
	        	List<TMyattentionorfans> tMyattentionorfansList=tMyattentionorfansService.findList(tMyattentionorfans);
	        	int i=0;
	        	for (TMyattentionorfans tMyattentionorfansTemp : tMyattentionorfansList) {
	        		i++;
	        		TUser t=tUserService.get(tMyattentionorfansTemp.getAttentionid());
	        		rm.put("user"+i, t);
				}
	        	return rm;
	        }
	 }
	 /**
	  * 用户关注
	  * @param attentionId 被关注者id
	  * @param fansId  粉丝id
	  * @return
	  */
	 @RequestMapping("/atteintion")
	 @ResponseBody
	 public ResultModel collectionCount(String attentionId,String fansId){
		 if(StringUtils.isNoneEmpty(attentionId)&&StringUtils.isNoneEmpty(fansId)){
			 TMyattentionorfans tMyattentionorfans=new TMyattentionorfans();
			 tMyattentionorfans.setAttentionid(attentionId);
			 tMyattentionorfans.setFansid(fansId);
			 tMyattentionorfansService.save(tMyattentionorfans);
			 return new ResultModel(0,"success",new LinkedHashMap());
		 }else{
			 return new ResultModel(1,"参数不正确",new LinkedHashMap());
		 }
		 
	 }
	 @RequestMapping("/collection/count")
	 @ResponseBody
	 public ResultModel collectionCount( String token){
		 return new ResultModel(0,"success",new LinkedHashMap()).put("code","1");
	 }
}
