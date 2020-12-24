package com.example.demo.service;

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
    public List<Map<Integer,Integer>> findAllMovie()
    {
        List<Map<Integer,Integer>>temp=new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>(1);
        List<year_release> temp1=yearReleaseRepository.findAll();
        for(year_release temp2:temp1)
        {
            map.put(temp2.getReleaseYear(),temp2.getReleaseNum());
        }
        temp.add(map);
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
    public List<Map<Integer,Integer>> findYearMovie(Integer year)
    {
        List<Map<Integer,Integer>>temp=new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>(1);
        List<year_month_release> temp1=yearmonthrepository.findByReleaseYearOrderByReleaseMonthAsc(year);
        for(year_month_release temp2:temp1)
        {
            map.put(temp2.getReleaseMonth(),temp2.getReleaseNum());
        }
        temp.add(map);
        return temp;
    }

}
