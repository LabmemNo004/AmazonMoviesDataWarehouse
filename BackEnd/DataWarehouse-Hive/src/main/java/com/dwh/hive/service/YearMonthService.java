package com.dwh.hive.service;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dwh.hive.pojo.YearMonthRelease;

public interface YearMonthService extends IService<YearMonthRelease> {

    JSONArray findYearMovie(Integer year);
}
