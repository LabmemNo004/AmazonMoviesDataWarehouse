package com.dwh.hive.service.serviceInterImpl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dwh.hive.dao.ReleaseTimeDAO;
import com.dwh.hive.pojo.Movie;
import com.dwh.hive.pojo.Product;
import com.dwh.hive.pojo.ReleaseTime;
import com.dwh.hive.service.ReleaseTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ReleaseServiceImpl
        extends ServiceImpl<ReleaseTimeDAO, ReleaseTime> implements ReleaseTimeService {

    @Autowired
    ReleaseTimeDAO ReleaseTimeRepository;

    /**
     * 组合查询时间导演类型
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

}
