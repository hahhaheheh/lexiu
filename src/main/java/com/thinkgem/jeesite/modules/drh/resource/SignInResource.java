package com.thinkgem.jeesite.modules.drh.resource;

import com.thinkgem.jeesite.common.utils.JedisUtils;
import com.thinkgem.jeesite.modules.drh.ResultModel;
import com.thinkgem.jeesite.modules.drh.entity.SignInRecord;
import com.thinkgem.jeesite.modules.drh.entity.TUser;
import com.thinkgem.jeesite.modules.drh.service.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedHashMap;

/**
 * Created by Administrator on 2017/11/6.
 */
@Controller
@RequestMapping("/signIn")
public class SignInResource {

    @Autowired
    private SignInService signInService;

    @RequestMapping("/recordAdd")
    @ResponseBody
    public ResultModel userSignIn(String token){
        TUser user = (TUser) JedisUtils.getObject(token);
        if (user==null){
            return new ResultModel(1000,"用户未登录",null);
        }else {
            return new ResultModel(0,"success",new LinkedHashMap());
        }
    }
}
