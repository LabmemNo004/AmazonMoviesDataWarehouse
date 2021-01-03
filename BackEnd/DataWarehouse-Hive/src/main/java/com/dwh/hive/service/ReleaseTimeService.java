package com.dwh.hive.service;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dwh.hive.pojo.Movie;
import com.dwh.hive.pojo.ReleaseTime;

import java.util.List;

public interface ReleaseTimeService extends IService<ReleaseTime> {
    JSONArray getTimeDirectorType (Integer year, String name, String type);
}
