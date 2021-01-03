package com.example.demo.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Entity.type;
import com.example.demo.dao.typeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfox.documentation.spring.web.json.Json;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class typeService {

    @Autowired
    private typeRepository typerepository;

    /**
     * 根据类别查找电影
     * @param type 类别
     * @return
     */
    public JSONArray getMovieByType(String type)
    {
        List<Map<String,Float>> temp=typerepository.getMovieByType(type);
        JSONArray temp1=new JSONArray();
        int i=1;
        for(Map<String,Float> t:temp){
            if(i<=50)
            {
                JSONObject alpha=new JSONObject();
                alpha.put("N",i);
                alpha.put("title",t.get("title"));
                alpha.put("score",t.get("score"));
                temp1.add(alpha);
            }
            i++;
        }
        JSONObject b=new JSONObject();
        b.put("总查询数量",i);
        temp1.add(b);
        return temp1;
    }

    /**
     * 查找受欢迎或小众的电影类别
     * @param up
     * @return
     */
    public JSONArray getPopularMovie(int up)
    {
        List<type> temp=new ArrayList<>();
        JSONArray some=new JSONArray();
        if(up>0)
        {
            temp=typerepository.
                    findByMovieNumGreaterThanOrderByMovieNumDesc(0);
        }
        else if(up==0)
        {
            temp=typerepository.
                    findByMovieNumGreaterThanOrderByMovieNumAsc(0);

        }
        int i=0;
        for(type s:temp)
        {
            i++;
            if(i<=50)
            {
                JSONObject x=new JSONObject();
                x.put("type",s.getType());
                x.put("movieNum",s.getMovieNum());
                x.put("N",i);
                some.add(x);
            }
        }
        JSONObject b=new JSONObject();
        b.put("总查询数量",i);
        some.add(b);
        return some;
    }

    /**
     * 根据评分查找电影
     * @param scores 类别
     * @return
     */
    public JSONArray getGreaterscoreMovie(Integer scores)
    {
        List<Map<String,String>> temp=typerepository.getGreaterScoreMovie(scores);
        JSONArray temp1=new JSONArray();
        int i=1;
        for(Map<String ,String>t :temp){
            if(i<=50)
            {
                JSONObject temp2=new JSONObject();
                temp2.put("N",i);
                temp2.put("title",t.get("title"));
                temp2.put("score",t.get("score"));
                temp1.add(temp2);
            }
            i++;
        }
        JSONObject b=new JSONObject();
        b.put("总查询数量",i);
        temp1.add(b);
        return temp1;
    }

    /**
     * 查找有正面评价电影
     * @return
     */
    public JSONArray getPositivemovie()
    {
        List<Map<String,String>> temp=typerepository.getPositiveMovie();
        JSONArray temp1=new JSONArray();
        int i=1;
        for(Map<String,String> t : temp){
            if(i<=50)
            {
                JSONObject temp2=new JSONObject();
                temp2.put("N",i);
                temp2.put("title",t.get("title"));
                temp2.put("score",t.get("score"));
                temp1.add(temp2);
            }
            i++;
        }
        JSONObject b=new JSONObject();
        b.put("总查询数量",i);
        temp1.add(b);
        return temp1;
    }


}
