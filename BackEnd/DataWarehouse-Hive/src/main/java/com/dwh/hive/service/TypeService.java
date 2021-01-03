package com.dwh.hive.service;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dwh.hive.pojo.Type;

public interface TypeService extends IService<Type> {

    JSONArray getMovieByType(String type);

    JSONArray getPopularMovie(int up);

    JSONArray getGreaterscoreMovie(Integer scores);

    JSONArray getPositivemovie();

    JSONArray getTimePopular (Integer year);

}
