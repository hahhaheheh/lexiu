package com.thinkgem.jeesite.modules.drh.service;

/**
 * Created by root on 2017/9/26.
 */
@Service("smsService")
public class SMSServiceImpl implements SMSService {

    @Override
    public void sendAuthCode(String mobile, String authCode) {
//        CCPRestSmsSDK restAPI = new CCPRestSmsSDK();
//        restAPI.init("app.cloopen.com", "8883");
//        restAPI.setAccount("aaf98fda444eab7d01444ec7e8d6001c", "6b4fe5a21e7b45eab225e6dac927c008");
//        restAPI.setAppId("aaf98f89444eaae2014457773cf10397");
//        restAPI.sendTemplateSMS(mobile,"1" ,new String[]{authCode,"15"});
    }
}
