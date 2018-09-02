package com.luoyifan.springbootoauthdemo.service;

import com.luoyifan.springbootoauthdemo.model.PageEntity;
import com.luoyifan.springbootoauthdemo.model.PageParam;
import com.luoyifan.springbootoauthdemo.model.dto.DataDictionaryDTO;
import com.luoyifan.springbootoauthdemo.model.po.UserPO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @Author: EvanLuo
 * @Date: 2018/8/15 13:16
 */
public interface UserService extends UserDetailsService{
    UserPO getById(Long id);

    List<UserPO> list(UserPO entity);

    PageEntity<UserPO> listPage(UserPO entity, PageParam pageParam);

    List<DataDictionaryDTO> listState();

    Long save(UserPO entity);

    void update(UserPO entity);

    void delete(Long id);
}
