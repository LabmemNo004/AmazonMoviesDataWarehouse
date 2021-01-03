package com.dwh.hive.service.serviceInterImpl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dwh.hive.dao.YearReleaseDAO;
import com.dwh.hive.pojo.YearRelease;
import com.dwh.hive.service.YearReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class YearReleaseServiceImpl
        extends ServiceImpl<YearReleaseDAO, YearRelease> implements YearReleaseService {

    @Autowired
    YearReleaseDAO yearReleaseRepository;

    /**
     * 返回所有电影的发布情况
     * @return
     */
    public JSONArray findAllMovie()
    {
        JSONArray temp=new JSONArray();
        List<YearRelease> temp1=yearReleaseRepository.findAllMovies();
        int i=1;
        for(YearRelease temp2:temp1)
        {
            if(i<=50)
            {
                JSONObject yearOne=new JSONObject();
                yearOne.put("N",i);
                yearOne.put("Year",temp2.getReleaseYear());
                yearOne.put("ReleaseNum",temp2.getReleaseNum());
                temp.add(yearOne);
            }
            i++;
        }
        JSONObject b=new JSONObject();
        b.put("总查询数量",i);
        temp.add(b);
        return temp;
    }

    /**
     * 返回有电影发布的年份
     * @return
     */
    public List<Integer> provideQualifiedYear()
    {
        List<Integer> temp=new ArrayList<>();
        List<YearRelease> temp1=yearReleaseRepository.selectList(null); //findAll()
        for (YearRelease temp2:temp1)
        {
            temp.add(temp2.getReleaseYear());
        }
        return temp;
    }


}
