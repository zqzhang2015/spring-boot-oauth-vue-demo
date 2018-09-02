package com.luoyifan.springbootoauthdemo.model;

import lombok.Data;

/**
 * @Author: EvanLuo
 * @Date: 2018/7/17 15:08
 */
@Data
public class BaseResult<T> {
    protected static final String SUCCESS_MSG = "操作成功";

    private T data;
    private String msg = SUCCESS_MSG;
    private Boolean success = true;

    public BaseResult() {

    }

    public BaseResult(T data) {
        this(true, SUCCESS_MSG, data);
    }

    public BaseResult(String msg) {
        this(true, msg, null);
    }

    public BaseResult(String msg, T data) {
        this(true, msg, data);
    }

    public BaseResult(boolean success, String msg) {
        this(success, msg, null);
    }

    public BaseResult(boolean success, String msg, T data) {
        this.data = data;
        this.msg = msg;
        this.success = success;
    }
}
