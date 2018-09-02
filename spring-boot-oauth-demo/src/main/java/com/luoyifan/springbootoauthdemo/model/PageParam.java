package com.luoyifan.springbootoauthdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: EvanLuo
 * @Date: 2018/8/20 17:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageParam {
    private Integer pageNum;
    private Integer pageSize;
}
