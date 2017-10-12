package com.thinkgem.jeesite.modules.drh.service;


import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.HttpReqUtils;
import com.thinkgem.jeesite.common.utils.TokenUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 2017/9/28.
 */
@Service("imService")
public class IMUserService {

    private String url ="http://a1.easemob.com/${ORG_NAME}/${APP_NAME}/users";

    public String regToIM(String username,String password){
        url.replace("${ORG_NAME}", Global.getConfig("orgName"))
           .replace("${APP_NAME}",Global.getConfig("appName"));
        Map body = new HashMap<String,String>();
        body.put("username",username);
        body.put("password",password);
        String resp = HttpReqUtils.postReq(url,body, TokenUtil.getAccessToken());
        return resp;
    }
}