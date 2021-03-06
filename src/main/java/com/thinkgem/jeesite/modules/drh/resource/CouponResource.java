package com.thinkgem.jeesite.modules.drh.resource;


import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.JedisUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.drh.ResultModel;
import com.thinkgem.jeesite.modules.drh.entity.*;
import com.thinkgem.jeesite.modules.drh.service.TCouponOrgService;
import com.thinkgem.jeesite.modules.drh.service.TCouponService;
import com.thinkgem.jeesite.modules.drh.service.TOrgService;
import com.thinkgem.jeesite.modules.drh.service.TUserCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Niexuyang on 2017/10/29.
 */
@Controller
@RequestMapping("/coupon")
public class CouponResource {

    @Autowired
    private TCouponOrgService tCouponOrgService;
    @Autowired
    private TCouponService tCouponService;
    @Autowired
    private TUserCouponService tUserCouponService;
    @Autowired
    private TOrgService tOrgService;
    /**
     *
     * 组织机构发放优惠券
     * @param token
     * @param couponId
     * @param start
     * @param end
     * @return
     */
    @RequestMapping("/org/grant")
    @ResponseBody
    public ResultModel orgGrantCoupon(String token,String couponId,String start,String end){


        TUser tUser = (TUser) JedisUtils.getObject(token);
        if(tUser==null){
            return new ResultModel(1000,"用户尚未登录",null);
        }
        if (StringUtils.isEmpty(tUser.getSid())||"1".equals(tUser.getSid())){
            return new ResultModel(2000,"您当前不是机构用户",null);
        }
        Date startDate,endDate =null;
        if (StringUtils.isEmpty(start)){
            startDate = new Date();
        }else {
            startDate = DateUtils.parseDate(start);
        }
        if (StringUtils.isEmpty(end)){
            endDate = DateUtils.addMonths(startDate,3);
        }else {
            endDate = DateUtils.parseDate(end);
        }
        TCouponOrg tCouponOrg = new TCouponOrg();
        tCouponOrg.setCouponid(couponId);
        tCouponOrg.setOrgid(tUser.getSid());
        tCouponOrg.setStartdate(startDate);
        tCouponOrg.setEnddate(endDate);
        tCouponOrgService.save(tCouponOrg);
        return new ResultModel(0,"success",null);
    }

    /**
     * 领取优惠券
     * @param token
     * @param orgCouponId
     * @return
     */
    @RequestMapping("/get")
    @ResponseBody
    public ResultModel getCoupon(String token,String orgCouponId){

        TUser tUser = (TUser) JedisUtils.getObject(token);
        if(tUser==null){
            return new ResultModel(1000,"用户尚未登录",null);
        }
        TCouponOrg tCouponOrg = tCouponOrgService.get(orgCouponId);
        TCoupon tCoupon = tCouponService.get(tCouponOrg.getCouponid());
        TOrg tOrg = tOrgService.get(tCouponOrg.getOrgid());
        TUserCoupon tmp = new TUserCoupon();
        List<TUserCoupon> tUsercouponList = tUserCouponService.findList(tmp);
        //查询用户是否已经领取过，领取过不可再领
        if(tUsercouponList!=null&&tUsercouponList.size()>0){
            return new ResultModel(2000,"不能重复领取",null);
        }
        TUserCoupon tUsercoupon = new TUserCoupon();
        tUsercoupon.setCouponid(tCoupon.getId());
        tUsercoupon.setEnddate(tCouponOrg.getEnddate());
        tUsercoupon.setFacevalue(tCoupon.getFacevalue());
        tUsercoupon.setName(tCoupon.getName());
        tUsercoupon.setOrgid(tCouponOrg.getOrgid());
        tUsercoupon.setOrgname(tOrg.getTitle());
        tUsercoupon.setStartdate(tCouponOrg.getStartdate());
        tUsercoupon.setCouponorgid(orgCouponId);
        tUsercoupon.setStatus(0);
//        tUsercoupon.setType();
        tUsercoupon.setUserid(tUser.getId());
        tUserCouponService.save(tUsercoupon);
        return new ResultModel(0,"success",null);
    }

    /**
     * 我的优惠券列表
     * @param token
     * @param status
     * @return
     */
    @RequestMapping("/myList")
    @ResponseBody
    public ResultModel listMyCoupon(String token,int status){
        TUser tUser = (TUser) JedisUtils.getObject(token);
        if(tUser==null){
            return new ResultModel(1000,"用户尚未登录",null);
        }
        TUserCoupon tUsercoupon = new TUserCoupon();
        tUsercoupon.setUserid(tUser.getId());
        tUsercoupon.setStatus(status);
        return new ResultModel(0,"success",new LinkedHashMap())
                .put("couponList",tUserCouponService.findList(tUsercoupon));
    }
}
