package com.example.demo.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Entity.*;
import com.example.demo.dao.releaseTimeRepository;
import com.example.demo.dao.typeRepository;
import com.example.demo.dao.yearMonthRepository;
import com.example.demo.dao.yearReleaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class timeService {

    @Autowired
    private releaseTimeRepository ReleaseTimeRepository;

    @Autowired
    private yearMonthRepository yearmonthrepository;

    @Autowired
    private com.example.demo.dao.yearReleaseRepository yearReleaseRepository;

    @Autowired
    private typeRepository typeRepository;



    /**
     * 返回所有电影的发布情况
     * @return
     */
    public JSONArray findAllMovie()
    {
        JSONArray temp=new JSONArray();
        List<year_release> temp1=yearReleaseRepository.findAllMovies();
        int i=1;
        for(year_release temp2:temp1)
        {
            if(i<=50)
            {
                JSONObject yearOne=new JSONObject();
                yearOne.put("N",i);
                yearOne.put("Year",temp2.getReleaseYear());
                yearOne.put("ReleaseNum",temp2.getReleaseNum());
                temp.add(yearOne);
            }
            i++;
        }
        JSONObject b=new JSONObject();
        b.put("总查询数量",i);
        temp.add(b);
        return temp;
    }

    /**
     * 返回有电影发布的年份
     * @return
     */
    public List<Integer> provideQualifiedYear()
    {
        List<Integer> temp=new ArrayList<>();
        List<year_release> temp1=yearReleaseRepository.findAll();
        for (year_release temp2:temp1)
        {
            temp.add(temp2.getReleaseYear());
        }
        return temp;
    }

    /**
     * 返回每年电影发布情况
     * @param year 年份
     * @return
     */
    public JSONArray findYearMovie(Integer year)
    {
        JSONArray temp=new JSONArray();
        List<year_month_release> temp1=yearmonthrepository.findYearMovie(year);
        int i=1;
        for(year_month_release temp2:temp1)
        {
            if(i<=50)
            {
                JSONObject yearOne=new JSONObject();
                yearOne.put("N",i);
                yearOne.put("month",i);
                yearOne.put("releaseNum",temp2.getReleaseNum());
                temp.add(yearOne);
            }
            i++;
        }
        JSONObject b=new JSONObject();
        b.put("总查询数量",i);
        temp.add(b);
        return temp;
    }

    /**
     * 组合查询时间导演类型
     * @param year
     * @param name
     * @param type
     * @return
     */
    public JSONArray getTimeDirectorType (Integer year, String name, String type)
    {
        JSONArray temp=new JSONArray();
        List<Map<String,Float>> temp1=ReleaseTimeRepository.
                getTimeDirectorType(year, name, type);
        int i=0;
        for(Map<String,Float> temp3:temp1)
        {
            i++;
            if(i<=50)
            {
                JSONObject temp4=new JSONObject();
                temp4.put("电影名称",temp3.get("title"));
                temp4.put("评分",temp3.get("score"));
                temp4.put("N",i);
                temp.add(temp4);
            }
        }
        JSONObject b=new JSONObject();
        b.put("总查询数量",i);
        temp.add(b);
        return temp;
    }

    /**
     * 组合查询某年最受欢迎的电影
     * @param year
     * @return
     */
    public JSONArray getTimePopular (Integer year)
    {
        JSONArray temp=new JSONArray();
        List<Map<String,Integer>> temp2=typeRepository.getTimeTypePopular(year);
        int i=0;
        for(Map<String,Integer> temp3:temp2)
        {
            i++;
            if(i<=50)
            {
                JSONObject temp4=new JSONObject();
                temp4.put("电影类别",temp3.get("type"));
                temp4.put("电影数量",temp3.get("movieNum"));
                temp4.put("N",i);
                temp.add(temp4);
            }
        }
        JSONObject b=new JSONObject();
        b.put("总查询数量",i);
        temp.add(b);
        return temp;
    }

}
