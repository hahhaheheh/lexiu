package com.lexiu.app.user.service;

import com.bm.entity.SessionEntity;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/10/15.
 */
public interface SessionService {

    SessionEntity getSessionEntity(String uuid, int type);

    void saveSessionEntiry(SessionEntity sessionEntity);
}
