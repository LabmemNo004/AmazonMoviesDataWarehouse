package com.example.demo.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Entity.*;
import com.example.demo.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class movieService {


    /**
     * 基本的构造函数，变量不应当超过十个
     * 超过十个应当自己再去细分。
     */
    @Autowired
    private relationRepository relationrepository;


    @Autowired
    private releaseTimeRepository releasetimerepository;

    @Autowired
    private movieRepository movierepository;

    @Autowired
    private productRepository productrepository;


    /**
     * 非sql查询，全部使用JPA 命名方法，未使用索引
     * @param year 年份
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
     * 返回电影的简单情况
     * @param title
     * @return
     */
    public JSONArray getSimpleMovie(String title)
    {
        JSONArray result = new JSONArray();
        List<movie> temp=movierepository.findAllByTitleOrderByScoreDesc(title);
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


    /**
     * 返回对应产品的情况
     * @param title
     * @return
     */
    public JSONArray getDetailProduct(String title)
    {
        JSONArray result = new JSONArray();
        List<movie> temp=movierepository.findAllByTitleOrderByScoreDesc(title);
        List<Integer> id=new ArrayList<>();
        for(movie temp1:temp)
        {
            id.add(temp1.getMovieID());
        }
        List<product> temp1=productrepository.findByMovieIDInOrderByTimeIDDesc(id);

        /**
         * 这里暂时用结果productID多次查询releasetime,不做链接查询。
         */
        int i =1;
        for(product s:temp1)
        {
            JSONObject studentOne = new JSONObject();
            studentOne.put("N", i);
            studentOne.put("type", s.getType());
            studentOne.put("format", s.getFormat());
            studentOne.put("ASIN", s.getProductID());
            studentOne.put("URL", "http://amazon.com/dp/"+s.getProductID().toString());
            release_time temp2=releasetimerepository.findByTimeID(s.getTimeID());
            JSONObject ReleaseTime = new JSONObject();
            ReleaseTime.put("Year",temp2.getReleaseYear());
            ReleaseTime.put("Month",temp2.getReleaseMonth());
            ReleaseTime.put("Day",temp2.getReleaseDay());
            studentOne.put("ReleaseTime",ReleaseTime);
            result.add(studentOne);
            i++;
        }
        return result;
    }

    /**
     *查找电影参演的演员或者导演
     * @param title 电影名
     * @return
     */
    public JSONObject getDirectorOrActor(String title,char identity)
    {
        /**
         * 自定义处理特殊排序
         */
        JSONObject result = new JSONObject();
        if(identity=='A')
        {
            List<String> temp=relationrepository.getAssociateActor(title,'T');
            //temp.sort(temp,new Comparator<String>());
            result.put("演员列表",temp);
        }
        else if(identity=='D')
        {
            List<String> temp=relationrepository.getAssociateDirector(title,'T');
            //temp.sort();
            result.put("导演列表", temp);
        }
        return result;
    }


}
