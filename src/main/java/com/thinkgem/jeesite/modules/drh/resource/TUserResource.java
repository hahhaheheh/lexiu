package com.thinkgem.jeesite.modules.drh.resource;

import com.thinkgem.jeesite.common.utils.CodeUtil;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.JedisUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.drh.ResultModel;
import com.thinkgem.jeesite.modules.drh.entity.TSession;
import com.thinkgem.jeesite.modules.drh.entity.TSignlnrecord;
import com.thinkgem.jeesite.modules.drh.entity.TUser;
import com.thinkgem.jeesite.modules.drh.service.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 * Created by Administrator on 2016/9/18.
 */
@Controller
@RequestMapping("/user")
public class TUserResource {

    @Autowired
    private TUserService userService;

    @Autowired
    private SMSService smsService;

    @Autowired
    private TSessionService sessionService;

    @Autowired
    private IMUserService imUserService;

    @Autowired
    private TSignlnrecordService tSignlnrecordService;
    /**
     * 用户注册
     * @return
     */
    @RequestMapping("/register")
    @ResponseBody
    public ResultModel register(String mobile, String password){
        TUser user = new TUser();
        user.setUsername(mobile);
        try{
            user = userService.getByObj(user);
            if(user==null){
                user = new TUser();
                user.setUsername(mobile);
                user.setPassword(password);
//                userService.save(user);
//                return new ResultModel(0,"success",new LinkedHashMap());
                if(imUserService.regToIM(mobile,mobile)!=null){
                    userService.save(user);
                    return new ResultModel(0,"success",new LinkedHashMap());
                }
                return new ResultModel(1,"环信注册异常",new LinkedHashMap());
            }else {
                return new ResultModel(1,"用户名已存在",new LinkedHashMap());
            }
        }catch (Exception e){
            return new ResultModel(1,"系统错误",new LinkedHashMap());
        }
    }

    /**
     * 验证码发送成功
     * @param request
     * @param mobile
     * @return
     */
    @RequestMapping("/send/validCode")
    @ResponseBody
    public ResultModel sendValidCode(HttpServletRequest request, String mobile){
//        TSession entiry = new TSession();
//        entiry.setUuid(mobile);
//        entiry.setType(0);
        try{
            String code = CodeUtil.getRandomStr(4);
//            entiry = this.sessionService.get(entiry);
//            if (entiry==null){
//                entiry = new TSession();
//                entiry.setUuid(mobile);
//                entiry.setContext(code);
//                entiry.setType(0);
//                entiry.setStatus(1);
//                entiry.setStartTime(new Date());
//            }else {
//                entiry.setStartTime(new Date());
//                entiry.setContext(code);
//            }
//            sessionService.save(entiry);
            JedisUtils.set("code"+mobile,code,60);
            smsService.sendAuthCode(mobile, code);
            return new ResultModel(0,"success",new LinkedHashMap()).put("code",code);
        }catch (Exception e){
            return new ResultModel(1,"系统错误",new LinkedHashMap());
        }
    }

    /**
     * 检查 验证码
     * @param request
     * @param mobile
     * @return
     */
    @RequestMapping("/check/validCode")
    @ResponseBody
    public ResultModel checkValidCode(HttpServletRequest request, String mobile, String code){
//        TSession sessionEntity = new TSession();
//        sessionEntity.setUuid(mobile);
//        sessionEntity.setType(0);
        try{
//            sessionEntity = this.sessionService.get(sessionEntity);
            String validCode = JedisUtils.get("code"+mobile);
            if (org.springframework.util.StringUtils.isEmpty(validCode)||!code.equals(validCode)){
                return new ResultModel(1,"验证码不正确",new LinkedHashMap());
            }else {
//                sessionEntity.setStatus(0);
//                this.sessionService.save(sessionEntity);
                return new ResultModel(0,"success",new LinkedHashMap());
            }
        }catch (Exception e){
            e.printStackTrace();
            return new ResultModel(1,"系统错误",new LinkedHashMap());
        }
    }

    /**
     * 用户登录
     * @param request
     * @param mobile
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public ResultModel login(HttpServletRequest request, String mobile, String password){
        TUser user = new TUser();
        user.setUsername(mobile);
        user.setPassword(password);
        try{
            user = userService.getByObj(user);
            if(user==null){
                return new ResultModel(1,"用户名和密码不匹配",new LinkedHashMap());
            }else {
//                TSession sessionEntity = new TSession();
//                sessionEntity.setUuid(mobile);
//                sessionEntity.setContext(user.getId());
//                sessionEntity.setType(1);
//                sessionEntity.setStatus(1);
//                sessionEntity.setStartTime(new Date());
//                sessionService.save(sessionEntity);
                String token = System.currentTimeMillis()+user.getId().toString();
                JedisUtils.setObject(token,user,0);
                return new ResultModel(0,"success",new LinkedHashMap())
                        .put("token",token).put("user",user);
            }
        }catch (Exception e){
            return new ResultModel(1,"系统错误",new LinkedHashMap());
        }
    }

    /**
     * 用户退出
     * @param token
     * @return
     */
    @RequestMapping("/logout")
    @ResponseBody
    public ResultModel logout(String token){
        TUser user = (TUser) JedisUtils.getObject(token);
        if (user==null){
            return new ResultModel(1000,"用户未登录",null);
        }else {
            JedisUtils.del(token);
            return new ResultModel(0,"success",new LinkedHashMap());
        }
    }
    /**
     * 重置登录密码
     * @param request
     * @return
     */
    @RequestMapping("/password/update")
    @ResponseBody
    public ResultModel findPwd(HttpServletRequest request, String token, String password){
        TUser user = (TUser) JedisUtils.getObject(token);
        if (user==null){
            return new ResultModel(1000,"用户未登录",null);
        }else {
            user.setPassword(password);
            userService.save(user);
            return new ResultModel(0,"success",new LinkedHashMap());
        }
    }


    /**
     * 查询所有用户
     * @return
     */
//    @RequestMapping("/list")
//    @ResponseBody
//    public ResultModel findAll(){
//        return new ResultModel(0,"success",new LinkedHashMap()).put("userList",userService.findAll());
//    }

    /**
     * 查询用户个人信息
     * @return
     */
    @RequestMapping("/info/myself")
    @ResponseBody
    public ResultModel getUserInfo(String token){
        TUser user = (TUser) JedisUtils.getObject(token);
        if (user==null){
            return new ResultModel(1000,"用户未登录",null);
        }
        return new ResultModel(0,"success",new LinkedHashMap()).put("user",user);
    }

    /**
     * 修改用户基本信息
     * @param token
     * @param user
     * @return
     */
    @RequestMapping("/info/update")
    @ResponseBody
    public ResultModel updateUserInfo(String token,TUser user){
        TUser tuser = (TUser) JedisUtils.getObject(token);
        if (tuser==null){
            return new ResultModel(1000,"用户未登录",null);
        }else {
//            user.setId(tuser.getId());
//            user.setUsername(tuser.getUsername());
//            user.setUmoney(tuser.getUmoney());
            tuser.setUseravatar(user.getUseravatar());
            tuser.setSignature(user.getSignature());
            tuser.setSex(user.getSex());
            tuser.setNickname(user.getNickname());
            userService.save(tuser);
            JedisUtils.setObject(token,tuser,0);
            return new ResultModel(0,"success",null);
        }
    }

    /**
     * 签到
     * @param token
     * @return
     */
    @RequestMapping("/sign")
    @ResponseBody
    public ResultModel signRecord(String token){
        TUser tuser = (TUser) JedisUtils.getObject(token);
        if (tuser==null){
            return new ResultModel(1000,"用户未登录",null);
        }else {
            Date date = new Date();
            TSignlnrecord tSignlnrecord = new TSignlnrecord();
            tSignlnrecord.setUserid(tuser.getId());
            tSignlnrecord.setSigndate(date);
            tSignlnrecord.setBeginDate(DateUtils.parseDate(DateUtils.formatDate(date)));
            tSignlnrecord.setEndDate(DateUtils.parseDate(DateUtils.formatDate(DateUtils.addDays(date,1))));
            TSignlnrecord tSignlnrecord1 = tSignlnrecordService.findUserRecord(tSignlnrecord);
            if (tSignlnrecord1!=null){
                return new ResultModel(2000,"本日已签过",null);
            }else {
                tSignlnrecordService.save(tSignlnrecord);
                return new ResultModel(0,"success",null);
            }
        }
    }

    /**
     * 是否签到
     * @param token
     * @return
     */
    @RequestMapping("/sign/record")
    @ResponseBody
    public ResultModel hasSignRecord(String token){
        TUser tuser = (TUser) JedisUtils.getObject(token);
        if (tuser==null){
            return new ResultModel(1000,"用户未登录",null);
        }else {
            Date date = new Date();
            TSignlnrecord tSignlnrecord = new TSignlnrecord();
            tSignlnrecord.setUserid(tuser.getId());
            tSignlnrecord.setSigndate(date);
            tSignlnrecord.setBeginDate(DateUtils.parseDate(DateUtils.formatDate(date)));
            tSignlnrecord.setEndDate(DateUtils.parseDate(DateUtils.formatDate(DateUtils.addDays(date,1))));
            TSignlnrecord tSignlnrecord1 = tSignlnrecordService.findUserRecord(tSignlnrecord);
            if (tSignlnrecord1!=null){
                return new ResultModel(0,"本日已签过",new LinkedHashMap()).put("hasSigned",true);
            }else {
                return new ResultModel(0,"本日未签",new LinkedHashMap()).put("hasSigned",false);
            }
        }
    }

    @RequestMapping("/third/login")
    @ResponseBody
    public ResultModel thirdLogin(String source ,String openId){
        TUser tUser =  new TUser();
        tUser.setSource(source);
        tUser.setOpenId(openId);
        Iterator<TUser> iterator = userService.findList(tUser).iterator();
        if (iterator.hasNext()){
            tUser=iterator.next();
            JedisUtils.setObject(openId,tUser,0);
            return new ResultModel(0,"success",new LinkedHashMap())
                    .put("token",openId).put("user",tUser);
        }else {
            if(imUserService.regToIM(openId,openId)!=null){
                userService.save(tUser);
                tUser=userService.findList(tUser).get(0);
                JedisUtils.setObject(openId,tUser,0);
                return new ResultModel(0,"success",new LinkedHashMap())
                        .put("token",openId).put("user",tUser);
            }
            return new ResultModel(1000,"注册异常",null);
        }
    }



}
