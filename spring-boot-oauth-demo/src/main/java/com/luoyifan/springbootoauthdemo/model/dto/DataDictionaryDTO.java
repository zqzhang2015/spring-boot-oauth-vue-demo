package com.luoyifan.springbootoauthdemo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: EvanLuo
 * @Description:
 * @Date: 2018/9/2 12:49
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataDictionaryDTO {
    private String name;
    private Integer value;
}
