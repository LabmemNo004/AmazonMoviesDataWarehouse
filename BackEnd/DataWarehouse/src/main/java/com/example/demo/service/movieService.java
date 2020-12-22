package com.example.demo.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Entity.*;
import com.example.demo.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class movieService {

    @Autowired
    private yearMonthRepository yearmonthrepository;

    @Autowired
    private releaseTimeRepository releasetimerepository;

    @Autowired
    private movieRepository movierepository;

    @Autowired
    private productRepository productrepository;

    @Autowired
    private yearReleaseRepository yearReleaseRepository;

    /**
     * 非sql查询，全部使用JPA 命名方法，未使用索引
     * @param year
     * @param month
     * @param day
     * @return
     */
    public List<movie> findBySpecifidTime(Integer year,Integer month,Integer day)
    {
        List<release_time> time= releasetimerepository.findByReleaseYearAndReleaseMonthAndReleaseDay(
                year, month, day);
        List<Integer> tempID=new ArrayList<>();
        List<Integer> tempID1=new ArrayList<>();
        for(release_time some:time)
        {
            tempID.add(some.getTimeID());
        }

        List<product> products=productrepository.findByTimeIDIn(tempID);

        for(product some:products)
        {
            tempID1.add(some.getMovieID());
        }

        List<movie>  temp=movierepository.findByMovieIDInOrderByScoreDesc(tempID1);

        return temp;
    }

    /**
     * 按年月日查找
     * @param year
     * @param month
     * @param day
     * @return
     */
    public List<Map<String,Float>> findBySpecificTime(
            Integer year,Integer month,Integer day)
    {
        if(month==0)
        {
            return movierepository.getAltimate3(year);
        }
        if(day==0)
        {
            return movierepository.getAltimate2(year, month);
        }
        return movierepository.getAltimate1(year, month, day);
    }

    /**
     * 按季度查询
     * @param year
     * @param season
     * @return
     */
    public List<Map<String,Float>> findBySpecificSeason(
            Integer year,Integer season)
    {
        List<Integer> temp=new ArrayList<>();
        if(season==1){
            for(int i=1;i<=3;++i)
            {
                temp.add(i);
            }
        }
        else if (season==2){
            for(int i=4;i<=6;++i)
            {
                temp.add(i);
            }
        }
        else if (season==3){
            for(int i=7;i<=9;++i)
            {
                temp.add(i);
            }
        }
        else if (season==4){
            for(int i=10;i<=12;++i)
            {
                temp.add(i);
            }
        }
        return movierepository.getAltimate4(year,temp);
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
     */
    public List<Map<Integer,Integer>> findYearMovie(Integer year)
    {
        List<Map<Integer,Integer>>temp=new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>(1);
        List<year_month_release> temp1=yearmonthrepository.findByReleaseYearOrderByReleaseMonthDesc(year);
        for(year_month_release temp2:temp1)
        {
            map.put(temp2.getReleaseMonth(),temp2.getReleaseNum());
        }
        temp.add(map);
        return temp;
    }

    /**
     * 返回所有电影的发布情况
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
     * 返回电影的简单情况
     */
    public JSONArray getSimpleMovie(String title)
    {
        JSONArray result = new JSONArray();
        List<movie> temp=movierepository.findAllByTitle(title);
        for(movie temp1:temp)
        {
            /**
             * 应该只循环一次。
             */
            JSONObject studentOne = new JSONObject();
            studentOne.put("productNum", temp1.getProductNum());
            studentOne.put("directorNum", temp1.getDirectorNum());
            studentOne.put("actorNum", temp1.getActorNum());
            studentOne.put("commentNum", temp1.getCommentNum());
            result.add(studentOne);
        }
        return result;
    }

}
