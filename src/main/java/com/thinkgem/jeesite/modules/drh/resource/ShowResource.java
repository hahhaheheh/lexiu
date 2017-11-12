package com.thinkgem.jeesite.modules.drh.resource;

import com.thinkgem.jeesite.common.utils.JedisUtils;
import com.thinkgem.jeesite.modules.drh.ResultModel;
import com.thinkgem.jeesite.modules.drh.entity.TUser;
import com.thinkgem.jeesite.modules.drh.entity.Watcher;
import com.thinkgem.jeesite.modules.drh.service.TUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedHashMap;

/**
 * Created by Niexuyang on 2017/11/12.
 */
@Controller
@RequestMapping("/show")
public class ShowResource {

    @Autowired
    private TUserService userService;

    @RequestMapping("/user/list")
    @ResponseBody
    public ResultModel getShowUserList(){
        return new ResultModel(0,"success",new LinkedHashMap())
                .put("userList",userService.findShowUsers(new TUser()));
    }

    @RequestMapping("/user/detail")
    @ResponseBody
    public ResultModel getShowUserDetail(String userId){

        TUser user = userService.get(userId);
        Watcher watcher = (Watcher) JedisUtils.getObject("watcher"+userId);
        return new ResultModel(0,"success",new LinkedHashMap())
                .put("watcher",watcher)
                .put("user",user);
    }
}
