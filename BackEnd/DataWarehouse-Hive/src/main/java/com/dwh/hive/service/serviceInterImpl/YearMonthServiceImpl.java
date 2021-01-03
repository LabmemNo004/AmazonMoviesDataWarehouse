package com.dwh.hive.service.serviceInterImpl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dwh.hive.dao.YearMonthDAO;
import com.dwh.hive.pojo.YearMonthRelease;
import com.dwh.hive.service.YearMonthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YearMonthServiceImpl
        extends ServiceImpl<YearMonthDAO, YearMonthRelease> implements YearMonthService {

    @Autowired
    YearMonthDAO yearmonthrepository;

    /**
     * 返回每年电影发布情况
     * @param year 年份
     */
    public JSONArray findYearMovie(Integer year)
    {
        JSONArray temp=new JSONArray();
        List<YearMonthRelease> temp1 = yearmonthrepository.findYearMovie(year);
        int i=1;
        for(YearMonthRelease temp2:temp1)
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
}
