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
    public JSONArray findBySpecificTime(
            Integer year,Integer month,Integer day)
    {
        List<Map<String, Float>> temp=new ArrayList<>();
        JSONArray a=new JSONArray();
        if(month==0)
        {
            temp=movierepository.getAltimate3(year);
            int i=0;
            for(Map<String, Float> temp1:temp)
            {
                ++i;
                if(i<=50)
                {
                    JSONObject b=new JSONObject();
                    b.put("名称",temp1.get("title"));
                    b.put("评分",temp1.get("score"));
                    b.put("N",i);
                    a.add(b);
                }
            }
            JSONObject b=new JSONObject();
            b.put("总查询数量",i);
            a.add(b);
            return a;
        }
        if(day==0)
        {
            temp=movierepository.getAltimate2(year, month);
            int i=0;
            for(Map<String, Float> temp1:temp)
            {
                ++i;
                if(i<=50)
                {
                    JSONObject b=new JSONObject();
                    b.put("名称",temp1.get("title"));
                    b.put("评分",temp1.get("score"));
                    b.put("N",i);
                    a.add(b);
                }

            }
            JSONObject b=new JSONObject();
            b.put("总查询数量",i);
            a.add(b);
            return a;
        }
        temp=movierepository.getAltimate1(year, month, day);
        int i=0;
        for(Map<String, Float> temp1:temp)
        {
            ++i;
            if(i<=50)
            {
                JSONObject b=new JSONObject();
                b.put("名称",temp1.get("title"));
                b.put("评分",temp1.get("score"));
                b.put("N",i);
                a.add(b);
            }
        }
        JSONObject b=new JSONObject();
        b.put("总查询数量",i);
        a.add(b);
        return a;
    }

    /**
     * 按季度查询
     * @param year
     * @param season
     * @return
     */
    public JSONArray findBySpecificSeason(
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
        JSONArray b=new JSONArray();
        List<Map<String, Float>> temps=movierepository.getAltimate4(year,temp);
        int i=0;
        for(Map<String, Float>a :temps)
        {
            ++i;
            if(i<=50)
            {
                JSONObject temp1=new JSONObject();
                temp1.put("名称",a.get("title"));
                temp1.put("评分",a.get("score"));
                temp1.put("N",i);
                b.add(temp1);
            }
        }
        JSONObject a=new JSONObject();
        a.put("总查询数量",i);
        b.add(a);
        return b;
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
        int i=1;
        for(movie temp1:temp)
        {
            /**
             * 应该只循环一次。
             */
            if(i<=50)
            {
                JSONObject studentOne = new JSONObject();
                studentOne.put("N", i);
                studentOne.put("productNum", temp1.getProductNum());
                studentOne.put("directorNum", temp1.getDirectorNum());
                studentOne.put("actorNum", temp1.getActorNum());
                studentOne.put("commentNum", temp1.getCommentNum());
                studentOne.put("score", temp1.getScore());
                result.add(studentOne);
            }
            i++;
        }
        JSONObject a=new JSONObject();
        a.put("总查询数量",i);
        result.add(a);
        return result;
    }

    /**
     * 返回电影的简单情况模糊匹配
     * @param title
     * @return
     */
    public JSONArray getSimpleMovieLike(String title)
    {
        JSONArray result = new JSONArray();
        List<movie> temp=movierepository.getSimpleMovieLike(title);
        int i=1;
        for(movie temp1:temp)
        {
            /**
             * 应该只循环一次。
             */
            if(i<=50)
            {
                JSONObject studentOne = new JSONObject();
                studentOne.put("N", i);
                studentOne.put("title", temp1.getTitle());
                studentOne.put("productNum", temp1.getProductNum());
                studentOne.put("directorNum", temp1.getDirectorNum());
                studentOne.put("actorNum", temp1.getActorNum());
                studentOne.put("commentNum", temp1.getCommentNum());
                studentOne.put("score", temp1.getScore());
                result.add(studentOne);
            }
            i++;
        }
        JSONObject a=new JSONObject();
        a.put("总查询数量",i);
        result.add(a);
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
            if(i<=50)
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
            }
            i++;
        }
        JSONObject a=new JSONObject();
        a.put("总查询数量",i);
        result.add(a);
        return result;
    }

    /**
     * 返回对应产品的情况
     * @param title
     * @return
     */
    public JSONArray getDetailProductLike(String title)
    {
        JSONArray result = new JSONArray();

        List<movie> temp=movierepository.getDetailProductLike(title);
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
            if(i<=50)
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
            }
            i++;
        }
        JSONObject a=new JSONObject();
        a.put("总查询数量",i);
        result.add(a);
        return result;
    }




    /**
     *查找电影参演的演员或者导演
     * @param title 电影名
     * @return
     */
    public JSONArray getDirectorOrActor(String title,char identity)
    {
        /**
         * 自定义处理特殊排序
         */
        int j=0;
        JSONArray result = new JSONArray();
        if(identity=='A')
        {
            List<String> temp=relationrepository.getAssociateActor(title,'T');
            //temp.sort(temp,new Comparator<String>());
            int i=1;
            for(String t : temp){
                if(i<=50)
                {
                    JSONObject alpha=new JSONObject();
                    alpha.put("N",i);
                    alpha.put("actor",t);
                    result.add(alpha);
                }
                i++;
            }
            j=i;
        }
        else if(identity=='D')
        {
            List<String> temp=relationrepository.getAssociateDirector(title,'T');
            //temp.sort();
            int i=1;
            for(String t : temp){
                if(i<=50)
                {
                    JSONObject alpha=new JSONObject();
                    alpha.put("N",i);
                    alpha.put("director",t);
                    result.add(alpha);
                }
                i++;
            }
            j=j+i;
        }
        JSONObject a=new JSONObject();
        a.put("总查询数量",j);
        result.add(a);
        return result;
    }

    /**
     *查找电影参演的演员或者导演
     * @param title 电影名
     * @return
     */
    public JSONArray getDirectorOrActorLike(String title,char identity)
    {
        /**
         * 自定义处理特殊排序
         */
        int j=0;
        JSONArray result = new JSONArray();
        if(identity=='A')
        {
            List<Map<String,String>> temp=
                    relationrepository.getAssociateActorLike(title,'T');
            int i=1;
            for(Map<String,String> t : temp){
                if(i<=50)
                {
                    JSONObject alpha=new JSONObject();
                    alpha.put("N",i);
                    alpha.put("MovieName",t.get("title"));
                    alpha.put("actor",t.get("name"));
                    result.add(alpha);
                }
                i++;
            }
            j=i;
        }
        else if(identity=='D')
        {
            List<Map<String,String>> temp=
                    relationrepository.getAssociateDirectorLike(title,'T');
            //temp.sort();
            int i=1;
            for(Map<String,String> t : temp){
                if(i<=50)
                {
                    JSONObject alpha=new JSONObject();
                    alpha.put("N",i);
                    alpha.put("MovieName",t.get("title"));
                    alpha.put("director",t.get("name"));
                    result.add(alpha);
                }
                i++;
            }
            j=j+i;
        }
        JSONObject a=new JSONObject();
        a.put("总查询数量",j);
        result.add(a);
        return result;
    }


}
