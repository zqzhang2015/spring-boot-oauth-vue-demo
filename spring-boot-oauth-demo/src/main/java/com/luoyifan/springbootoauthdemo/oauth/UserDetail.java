package com.luoyifan.springbootoauthdemo.oauth;


import com.luoyifan.springbootoauthdemo.model.po.UserPO;

import java.util.Collections;

/**
 * @Author: EvanLuo
 * @Date: 2018/8/16 12:51
 */
public class UserDetail extends org.springframework.security.core.userdetails.User {

    private UserPO userPO;

    public UserDetail(UserPO userPO) {
        super(userPO.getUsername(), userPO.getPassword(), true, true, true, true, Collections.EMPTY_SET);
        this.userPO = userPO;
    }

    public UserPO getUserPO() {
        return userPO;
    }
}
