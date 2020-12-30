package com.example.demo.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Entity.*;
import com.example.demo.dao.releaseTimeRepository;
import com.example.demo.dao.yearMonthRepository;
import com.example.demo.dao.yearReleaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class timeService {

    @Autowired
    private yearMonthRepository yearmonthrepository;

    @Autowired
    private com.example.demo.dao.yearReleaseRepository yearReleaseRepository;

    /**
     * 返回所有电影的发布情况
     * @return
     */
    public JSONArray findAllMovie()
    {
        JSONArray temp=new JSONArray();
        List<year_release> temp1=yearReleaseRepository.findAll();
        int i=1;
        for(year_release temp2:temp1)
        {
            JSONObject yearOne=new JSONObject();
            yearOne.put("N",i);
            yearOne.put("Year",temp2.getReleaseYear());
            yearOne.put("ReleaseNum",temp2.getReleaseNum());
            temp.add(yearOne);
            i++;
        }

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
        List<year_month_release> temp1=yearmonthrepository.findByReleaseYearOrderByReleaseMonthAsc(year);
        int i=1;
        for(year_month_release temp2:temp1)
        {
            JSONObject yearOne=new JSONObject();
            yearOne.put("N",i);
            yearOne.put("year",i);
            yearOne.put("releaseNum",temp2.getReleaseNum());
            temp.add(yearOne);
            i++;
        }
        return temp;
    }

}
