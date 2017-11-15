package com.thinkgem.jeesite.modules.drh.resource;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.JedisUtils;
import com.thinkgem.jeesite.modules.drh.ResultModel;
import com.thinkgem.jeesite.modules.drh.entity.TLiveroom;
import com.thinkgem.jeesite.modules.drh.entity.TUser;
import com.thinkgem.jeesite.modules.drh.entity.Watcher;
import com.thinkgem.jeesite.modules.drh.service.TLiveroomService;
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

    @Autowired
    private TLiveroomService tLiveroomService;

    /**
     * 直播房间列表
     * @return
     */
    @RequestMapping("/user/list")
    @ResponseBody
    public ResultModel getShowUserList(){
        Page<TLiveroom> page= new Page<TLiveroom>(0,10);
        return new ResultModel(0,"success",new LinkedHashMap())
                .put("roomList",tLiveroomService.findList(new TLiveroom()));
    }

    /**
     * 直播房间详情
     * @return
     */
    @RequestMapping("/user/detail")
    @ResponseBody
    public ResultModel getShowUserDetail(String roomId){
        TLiveroom tLiveroom = tLiveroomService.get(roomId);
        TUser user = userService.get(tLiveroom.getUserid());
        Watcher watcher = (Watcher) JedisUtils.getObject("watcher"+roomId);
        return new ResultModel(0,"success",new LinkedHashMap())
                .put("watcher",watcher)
                .put("user",user)
                .put("room",tLiveroom);
    }

    /**
     * 创建直播房间-推流
     * @param token
     * @param tLiveroom
     * @return
     */
    @RequestMapping("/user/room/create")
    @ResponseBody
    public ResultModel pushStorm(String token,TLiveroom tLiveroom){
        TUser tuser = (TUser) JedisUtils.getObject(token);
        if (tuser==null){
            return new ResultModel(1000,"用户未登录",null);
        }else {
            tLiveroomService.save(tLiveroom);
            return new ResultModel(0,"success",null);
        }
    }

}
