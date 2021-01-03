package com.dwh.hive.service.serviceInterImpl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dwh.hive.dao.MovieDAO;
import com.dwh.hive.dao.ProductDAO;
import com.dwh.hive.dao.RelationDAO;
import com.dwh.hive.dao.ReleaseTimeDAO;
import com.dwh.hive.pojo.Movie;
import com.dwh.hive.pojo.Product;
import com.dwh.hive.pojo.ReleaseTime;
import com.dwh.hive.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MovieServiceImpl extends ServiceImpl<MovieDAO, Movie> implements MovieService {

    @Autowired
    private ReleaseTimeDAO releaseTimeDAO;

    @Autowired
    private MovieDAO movieDAO;

    @Autowired
    private ProductDAO productDAO;


    public List<Movie> findBySpecifidTime(Integer year, Integer month, Integer day)
    {
        List<ReleaseTime> time= releaseTimeDAO.findByReleaseYearAndReleaseMonthAndReleaseDay(
                year, month, day);

        List<Integer> tempID=new ArrayList<>();
        List<Integer> tempID1=new ArrayList<>();

        for(ReleaseTime some:time)
        {
            tempID.add(some.getTimeID());
        }

        List<Product> products=productDAO.findByTimeIDIn(tempID);

        for(Product some:products)
        {
            tempID1.add(some.getMovieID());
        }

        List<Movie>  temp=movieDAO.findByMovieIDInOrderByScoreDesc(tempID1);

        return temp;
    }

    /**
     * 按年月日查找
     */
    public JSONArray findBySpecificTime(Integer year,Integer month,Integer day)
    {
        List<Map<String, Float>> temp=new ArrayList<>();
        JSONArray a=new JSONArray();
        if(month==0)
        {
            temp=movieDAO.getAltimate3(year);
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
            temp=movieDAO.getAltimate2(year, month);
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
        temp=movieDAO.getAltimate1(year, month, day);
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
     */
    public JSONArray findBySpecificSeason(Integer year,Integer season)
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
        List<Map<String, Float>> temps=movieDAO.getAltimate4(year,temp);
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
     */
    public JSONArray getSimpleMovie(String title)
    {
        JSONArray result = new JSONArray();
        List<Movie> temp=movieDAO.findAllByTitleOrderByScoreDesc(title);
        int i=1;
        for(Movie temp1:temp)
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
     * 返回对应产品的情况
     */
    public JSONArray getDetailProduct(String title)
    {
        JSONArray result = new JSONArray();
        List<Movie> temp=movieDAO.findAllByTitleOrderByScoreDesc(title);
        List<Integer> id=new ArrayList<>();
        for(Movie temp1:temp)
        {
            id.add(temp1.getMovieID());
        }
        List<Product> temp1=productDAO.findByMovieIDInOrderByTimeIDDesc(id);

        /**
         * 这里暂时用结果productID多次查询releasetime,不做链接查询。
         */
        int i =1;
        for(Product s:temp1)
        {
            if(i<=50)
            {
                JSONObject studentOne = new JSONObject();
                studentOne.put("N", i);
                studentOne.put("type", s.getType());
                studentOne.put("format", s.getFormat());
                studentOne.put("ASIN", s.getProductID());
                studentOne.put("URL", "http://amazon.com/dp/"+s.getProductID().toString());
                ReleaseTime temp2 = releaseTimeDAO.findByTimeID(s.getTimeID());
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

}
