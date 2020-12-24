package com.example.demo.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.JSONAndConfig.JsonResult;
import com.example.demo.dao.typeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class typeService {

    @Autowired
    private typeRepository typerepository;

    /**
     * 根据类别查找电影
     * @param type 类别
     * @return
     */
    public JSONObject getMovieByType(String type)
    {
        JSONArray temp=typerepository.getMovieByType(type);
        JSONObject temp2=new JSONObject();
        temp2.put("电影数量",temp.size());
        temp2.put("电影列表",temp);
        return temp2;
    }

    /**
     * 查找受欢迎或小众的电影类别
     * @param up
     * @return
     */
    public JSONArray getPopularMovie(int up)
    {
        JSONArray temp=new JSONArray();
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
        return temp;
    }

    /**
     * 根据评分查找电影
     * @param scores 类别
     * @return
     */
    public JSONObject getGreaterscoreMovie(Integer scores)
    {
        JSONArray temp=typerepository.getGreaterScoreMovie(scores);
        JSONObject temp2=new JSONObject();
        temp2.put("电影数量","限定20");
        temp2.put("电影列表",temp);
        return temp2;
    }

    /**
     * 查找有正面评价电影
     * @return
     */
    public JSONObject getPositivemovie()
    {
        JSONArray temp=typerepository.getPositiveMovie();
        JSONObject temp2=new JSONObject();
        temp2.put("电影数量","限定20");
        temp2.put("电影列表",temp);
        return temp2;
    }


}
