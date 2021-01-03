package com.dwh.hive.service;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dwh.hive.pojo.Movie;

import java.util.List;

public interface MovieService extends IService<Movie> {

    List<Movie> findBySpecifidTime(Integer year, Integer month, Integer day);

    JSONArray findBySpecificTime(Integer year,Integer month,Integer day);

    JSONArray findBySpecificSeason(Integer year,Integer season);

    JSONArray getSimpleMovie(String title);

    JSONArray getDetailProduct(String title);
}
