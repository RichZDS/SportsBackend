package com.zds.sports.model.vo;

import lombok.Data;
import java.util.List;

@Data
public class PageResultVO<T> {
    private long total;
    private long size;
    private long page;
    private long pages;
    private List<T> records;
    private List<T> list; // 兼容前端使用的list字段

    public PageResultVO() {}

    public PageResultVO(long total, long size, long current, long pages, List<T> records) {
        this.total = total;
        this.size = size;
        this.page = current;
        this.pages = pages;
        this.records = records;
        this.list = records; // 同时设置list字段
    }
}
