package com.luoyifan.springbootoauthdemo.model;

import com.github.pagehelper.Page;
import lombok.Data;

import java.util.List;

/**
 * @Author: EvanLuo
 * @Date: 2018/8/20 13:36
 */
@Data
public class PageEntity<T> {
    private List<T> rows;
    private Long total;
    public PageEntity(){
    }
    public PageEntity(Page<T> page){
        this(page.getResult(),page.getTotal());
    }
    public PageEntity(List<T> rows,Long total){
        this.rows=rows;
        this.total=total;
    }
}
