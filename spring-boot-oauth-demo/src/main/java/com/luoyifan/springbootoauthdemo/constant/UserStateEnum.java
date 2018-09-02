package com.luoyifan.springbootoauthdemo.constant;

/**
 * @Author: EvanLuo
 * @Description:
 * @Date: 2018/9/2 12:47
 */
public enum UserStateEnum {
    NORMAL("正常",0),
    DISABLED("禁用",1);

    private String name;
    private Integer value;
    UserStateEnum(String name, Integer value){
        this.name=name;
        this.value=value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
