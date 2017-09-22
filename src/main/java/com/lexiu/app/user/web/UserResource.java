package com.lexiu.app.user.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.LinkedHashMap;

/**
 * Created by Administrator on 2016/9/18.
 */
@Controller
@RequestMapping("/user")
@Api(value = "user",description = "用户模块")
public class UserResource {

    @Autowired
    private UserService userService;

    @Autowired
    private SMSService smsService;

    @Autowired
    private SessionService sessionService;

    /**
     * 用户注册
     * @return
     */
    @RequestMapping("/register")
    @ResponseBody
    @ApiOperation(value = "用户注册",httpMethod = "POST")
    public ResultModel register(
            @ApiParam(required = true,name = "mobile",value = "用户名（手机号）") String mobile,
            @ApiParam(required = true,name = "password",value = "密码") String password
            ){
        try{
            User user = userService.getUserByUserName(mobile);
            if(user==null){
                user = new User();
                user.setUserName(mobile);
                user.setPassword(password);
                userService.register(user);
                return new ResultModel(0,"success",new LinkedHashMap());
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
    @ApiOperation(value = "发送验证码",httpMethod = "GET")
    public ResultModel sendValidCode(HttpServletRequest request,
                                     @ApiParam(required = true,name = "mobile",value = "手机号") String mobile){
        try{

            String code = CodeUtil.getRandomStr(4);
            SessionEntity entiry = this.sessionService.getSessionEntity(mobile,0);
            if (entiry==null){
                entiry = new SessionEntity(mobile,code,0,1,new Date());
            }else {
                entiry.setStartTime(new Date());
                entiry.setContext(code);
            }
            sessionService.saveSessionEntiry(entiry);
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
    @ApiOperation(value = "检查验证码",httpMethod = "POST")
    public ResultModel checkValidCode(HttpServletRequest request,
                                      @ApiParam(required = true,name = "mobile",value = "手机号")String mobile,
                                      @ApiParam(required = true,name = "code",value = "验证码")String code){
        try{
            SessionEntity sessionEntity = this.sessionService.getSessionEntity(mobile,0);
            if (sessionEntity==null||!code.equals(sessionEntity.getContext())){
                return new ResultModel(1,"验证码不正确",new LinkedHashMap());
            }else {
                sessionEntity.setStatus(0);
                this.sessionService.saveSessionEntiry(sessionEntity);
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
    @ApiOperation(value = "用户登录",httpMethod = "POST")
    public ResultModel login(HttpServletRequest request,
                                      @ApiParam(required = true,name = "mobile",value = "手机号")String mobile,
                                      @ApiParam(required = true,name = "password",value = "密码")String password){
        try{
            User user = userService.getLoginUser(mobile,password);
            if(user==null){
                return new ResultModel(1,"用户名和密码不匹配",new LinkedHashMap());
            }else {
                SessionEntity sessionEntity = new SessionEntity(mobile,user.getId()+"",1,1,new Date());
                sessionService.saveSessionEntiry(sessionEntity);
                return new ResultModel(0,"success",new LinkedHashMap()).put("user",user);
            }
        }catch (Exception e){
            return new ResultModel(1,"系统错误",new LinkedHashMap());
        }
    }
    /**
     * 重置登录密码
     * @param request
     * @param mobile
     * @return
     */
    @RequestMapping("/password/update")
    @ResponseBody
    @ApiOperation(value = "找回密码",httpMethod = "POST")
    public ResultModel findPwd(HttpServletRequest request,
                               @ApiParam(required = true,name = "mobile",value = "手机号")String mobile,
                               @ApiParam(required = true,name = "password",value = "密码")String password){
        User user = userService.getUserByUserName(mobile);
        if(user==null){
            return new ResultModel(1,"该用户不存在",new LinkedHashMap());
        }else {
            user.setPassword(password);
            userService.updateUser(user);
            return new ResultModel(0,"success",new LinkedHashMap());
        }
    }

    /**
     * 完善个人信息
     * @param request
     * @return
     */
    @RequestMapping("/info/update")
    @ResponseBody
    @ApiOperation(value = "完善个人信息",httpMethod = "POST")
    public ResultModel updateUserInfo(HttpServletRequest request,
                               @ApiParam(required = true,name = "userId",value = "用户id")Long userId,
                               @ApiParam(required = true,name = "name",value = "姓名")String name,
                               @ApiParam(required = true,name = "age",value = "年龄")Integer age,
                               @ApiParam(required = true,name = "height",value = "身高")Float height,
                               @ApiParam(required = true,name = "weight",value = "体重")Float weight,
                               @ApiParam(required = true,name = "waistline",value = "腰围")Float waistline,
                               @ApiParam(required = true,name = "gender",value = "性别（M女 F男）")String gender){
        User user = userService.getById(userId);
        if(user==null){
            return new ResultModel(1,"该用户不存在",new LinkedHashMap());
        }else {
            user.setName(name);
            user.setAge(age);
            user.setHeight(height);
            user.setWeight(weight);
            user.setWaistline(waistline);
            user.setGender(gender);
            userService.updateUser(user);
            return new ResultModel(0,"success",new LinkedHashMap());
        }
    }

    /**
     * 查询所有用户
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    @ApiOperation(value = "查询所有用户",httpMethod = "GET")
    public ResultModel findAll(){
        return new ResultModel(0,"success",new LinkedHashMap()).put("userList",userService.findAll());
    }

    /**
     * 查询用户个人信息
     * @return
     */
    @RequestMapping("/info/myself")
    @ResponseBody
    @ApiOperation(value = "查询用户个人信息",httpMethod = "GET")
    public ResultModel getUserInfo( @ApiParam(required = true,name = "userId",value = "用户id")Long userId){
        return new ResultModel(0,"success",new LinkedHashMap()).put("user",userService.getById(userId));
    }

}
