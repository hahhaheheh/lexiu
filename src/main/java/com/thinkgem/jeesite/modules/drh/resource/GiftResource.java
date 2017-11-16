package com.thinkgem.jeesite.modules.drh.resource;

import com.thinkgem.jeesite.common.utils.JedisUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.drh.ResultModel;
import com.thinkgem.jeesite.modules.drh.entity.Gift;
import com.thinkgem.jeesite.modules.drh.entity.GiftRecord;
import com.thinkgem.jeesite.modules.drh.entity.TUser;
import com.thinkgem.jeesite.modules.drh.service.GiftRecordService;
import com.thinkgem.jeesite.modules.drh.service.GiftService;
import com.thinkgem.jeesite.modules.drh.service.TUserService;
import org.codehaus.groovy.util.ListHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.util.Date;

/**
 * Created by Niexuyang on 2017/11/15.
 */
@Controller
@RequestMapping("/gift")
public class GiftResource {

    @Autowired
    private GiftService giftService;

    @Autowired
    private TUserService tUserService;

    @Autowired
    private GiftRecordService giftRecordService;
    /**
     * 礼物列表
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public ResultModel giftList(){
        return new ResultModel(0,"success",new ListHashMap())
                .put("giftList",giftService.findList(new Gift()));
    }

    /**
     * 送礼物
     * @param token
     * @param userId
     * @param giftId
     * @return
     */
    @RequestMapping("/send")
    @ResponseBody
    public ResultModel sendGift(String token,String userId,String giftId){
        TUser tUser = (TUser) JedisUtils.getObject(token);

        if (tUser==null){
            return new ResultModel(1000,"用户未登录",null);
        }
        TUser user = tUserService.get(userId);
        if(user==null){
            return new ResultModel(2000,"主播不存在",null);
        }

        GiftRecord giftRecord = new GiftRecord(userId,tUser.getId(),giftId,new Date());
        giftRecordService.save(giftRecord);
        return new ResultModel(0,"success",null);
    }

    /**
     * 查询我的礼物列表
     * @param token
     * @return
     */
    @RequestMapping("/myGifts")
    @ResponseBody
    public ResultModel getMyGiftList(String token){
        TUser tUser = (TUser) JedisUtils.getObject(token);

        if (tUser==null){
            return new ResultModel(1000,"用户未登录",null);
        }
        if(StringUtils.isEmpty(tUser.getSid())){
            return new ResultModel(2000,"您当前不是还主播",null);
        }
        GiftRecord giftRecord = new GiftRecord();
        giftRecord.setUserid(tUser.getId());
        return new ResultModel(0,"success",new ListHashMap())
                .put("myGifts",giftRecordService.findList(giftRecord));
    }
}
