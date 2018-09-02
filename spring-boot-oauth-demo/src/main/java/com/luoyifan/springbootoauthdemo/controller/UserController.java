package com.luoyifan.springbootoauthdemo.controller;

import com.luoyifan.springbootoauthdemo.model.BaseResult;
import com.luoyifan.springbootoauthdemo.model.PageParam;
import com.luoyifan.springbootoauthdemo.model.po.UserPO;
import com.luoyifan.springbootoauthdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: EvanLuo
 * @Date: 2018/8/20 13:08
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public BaseResult list(UserPO entity) {
        return new BaseResult<>(userService.list(entity));
    }

    @GetMapping("/listPage")
    public BaseResult listPage(UserPO entity, PageParam pageParam) {
        return new BaseResult<>(userService.listPage(entity, pageParam));
    }

    @GetMapping("/listState")
    public BaseResult listState() {
        return new BaseResult<>(userService.listState());
    }

    @PostMapping("/save")
    public BaseResult save(@RequestBody UserPO entity) {
        return new BaseResult<>(userService.save(entity));
    }

    @PostMapping("/update")
    public BaseResult update(@RequestBody UserPO entity) {
        userService.update(entity);
        return new BaseResult();
    }

    @PostMapping("/delete")
    public BaseResult delete(Long id) {
        userService.delete(id);
        return new BaseResult();
    }

}
