package com.luoyifan.springbootoauthdemo.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.luoyifan.springbootoauthdemo.constant.UserStateEnum;
import com.luoyifan.springbootoauthdemo.dao.UserDao;
import com.luoyifan.springbootoauthdemo.model.PageEntity;
import com.luoyifan.springbootoauthdemo.model.PageParam;
import com.luoyifan.springbootoauthdemo.model.dto.DataDictionaryDTO;
import com.luoyifan.springbootoauthdemo.model.po.UserPO;
import com.luoyifan.springbootoauthdemo.oauth.UserDetail;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: EvanLuo
 * @Date: 2018/8/16 12:47
 */
@Primary
@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserPO getById(Long id) {
        return userDao.selectByPrimaryKey(id);
    }

    @Override
    public List<UserPO> list(UserPO entity) {
        Example example = new Example(UserPO.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(entity.getUsername())) {
            criteria.andLike("username", "%" + entity.getUsername() + "%");
        }
        if (StringUtils.isNotBlank(entity.getNickname())) {
            criteria.andLike("nickname", "%" + entity.getNickname() + "%");
        }
        if (StringUtils.isNotBlank(entity.getMobile())) {
            criteria.andLike("mobile", "%" + entity.getMobile() + "%");
        }
        return userDao.selectByExample(example);
    }

    @Override
    public PageEntity<UserPO> listPage(UserPO entity, PageParam pageParam) {
        Page<UserPO> page = PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize())
                .doSelectPage(() -> list(entity));
        return new PageEntity<>(page);
    }

    @Override
    public List<DataDictionaryDTO> listState() {
        return Arrays.stream(UserStateEnum.values())
                .map(userStateEnum -> new DataDictionaryDTO(userStateEnum.getName(), userStateEnum.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserPO userPO = userDao.selectByUserName(s);
        if (userPO == null) {
            throw new UsernameNotFoundException("用户名或密码不存在");
        }
        return new UserDetail(userPO);
    }

    @Override
    public Long save(UserPO entity) {
        userDao.insertSelective(entity);
        return entity.getId();
    }

    @Override
    public void update(UserPO entity) {
        userDao.updateByPrimaryKeySelective(entity);
    }

    @Override
    public void delete(Long id) {
        userDao.deleteByPrimaryKey(id);
    }
}
