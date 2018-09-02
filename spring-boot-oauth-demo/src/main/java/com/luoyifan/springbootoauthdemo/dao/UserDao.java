package com.luoyifan.springbootoauthdemo.dao;

import com.luoyifan.springbootoauthdemo.model.po.UserPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.ExampleMapper;

/**
 * @Author: EvanLuo
 * @Date: 2018/8/15 13:23
 */
@Mapper
public interface UserDao extends BaseMapper<UserPO>,ExampleMapper<UserPO> {

    @Select("SELECT * FROM user WHERE username=#{username}")
    UserPO selectByUserName(String username);

}
