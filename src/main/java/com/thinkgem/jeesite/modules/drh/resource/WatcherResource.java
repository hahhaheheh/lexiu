package com.thinkgem.jeesite.modules.drh.resource;

import com.thinkgem.jeesite.common.utils.JedisUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.drh.ResultModel;
import com.thinkgem.jeesite.modules.drh.entity.TUser;
import com.thinkgem.jeesite.modules.drh.entity.Watcher;
import com.thinkgem.jeesite.modules.drh.service.TUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Niexuyang on 2017/11/12.
 */
@Controller
@RequestMapping("/watcher")
public class WatcherResource {

    @Autowired
    private TUserService tUserService;

    @RequestMapping("/add")
    @ResponseBody
    public void addWatcher(String userId,String watcherId){
        Watcher watcher = (Watcher) JedisUtils.getObject("watcher"+userId);

        if (watcher==null){
            watcher = new Watcher();
        }
        if (!StringUtils.isEmpty(watcherId)){
            TUser tUser = tUserService.get(watcherId);
            watcher.addOnWatcher(tUser);
        }else {
            watcher.addOffWatcher();
        }

        JedisUtils.setObject("watcher"+userId,watcher,0);
    }

    @RequestMapping("/sub")
    @ResponseBody
    public void subWatcher(String userId,String watcherId){
        Watcher watcher = (Watcher) JedisUtils.getObject("watcher"+userId);

//        if (watcher==null){
//            watcher = new Watcher();
//        }
        if (!StringUtils.isEmpty(watcherId)){
            TUser tUser = tUserService.get(watcherId);
            watcher.subOnWatcher(tUser);
        }else {
            watcher.subOffWatcher();
        }

        JedisUtils.setObject("watcher"+userId,watcher,0);
    }
}
