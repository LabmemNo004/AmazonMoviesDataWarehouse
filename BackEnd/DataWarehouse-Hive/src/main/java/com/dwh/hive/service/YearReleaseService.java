package com.dwh.hive.service;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dwh.hive.pojo.YearRelease;

import java.util.List;

public interface YearReleaseService extends IService<YearRelease> {

    JSONArray findAllMovie();

    List<Integer> provideQualifiedYear();
}
