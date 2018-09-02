package com.luoyifan.springbootoauthdemo.oauth;


import com.luoyifan.springbootoauthdemo.model.po.UserPO;

/**
 * @Author: EvanLuo
 * @Date: 2018/8/17 12:51
 */
public class OAuthManager {

    public final static ThreadLocal<UserDetail> OAUTH_USER_DETAIL = new ThreadLocal<>();

    public static UserDetail getUserDetail(){
        return OAUTH_USER_DETAIL.get();
    }

    public static UserPO getUser(){
        return getUserDetail().getUserPO();
    }

    public static Long getUserId(){
        return getUser().getId();
    }
}
