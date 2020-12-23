package com.example.demo.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Entity.person;
import com.example.demo.dao.cooperationRepository;
import com.example.demo.dao.personRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class personService {
    @Autowired
    private personRepository personrepository;

    @Autowired
    private cooperationRepository cooperationrepository
;
    public List<person> findAll()
    {
        return personrepository.findAll();
    }

    /**
     * 根据导演名找到他指导的电影。
     * @param name
     * @return
     */
    public JSONObject getDirectorWork(String name)
    {
        List<Map<String, Float>> temp=personrepository.getMoviesByDirector(name);
        int some=temp.size();
        JSONObject temp1=new JSONObject();
        temp1.put("电影数量",some);
        temp1.put("电影列表",temp);
        return temp1;
    }

    /**
     * 查找拍摄电影数目大于一定数目的导演
     * @param number
     * @return
     */
    public JSONArray getGreaterDirector(Integer number)
    {
        JSONArray temp=new JSONArray();
        List<person> persons=personrepository.findByDirectorNumGreaterThanEqualOrderByDirectorNumDesc(number);
        for(person temp1:persons)
        {
            JSONObject temp2=new JSONObject();
            temp2.put("导演名称",temp1.getName());
            temp2.put("参与电影数量",temp1.getDirectorNum());
            temp2.put("评分",temp1.getDirectorAverageScore());
            temp.add(temp2);
        }
        return temp;
    }

    /**
     * 根据导演名找到他指导的电影。
     * @param name
     * @return
     */
    public JSONObject getActorWork(String name)
    {
        List<Map<String, Float>> temp=personrepository.getMoviesByActor(name);
        int some=temp.size();
        JSONObject temp1=new JSONObject();
        temp1.put("电影数量",some);
        temp1.put("电影列表",temp);
        return temp1;
    }

    /**
     * 查找参加电影数目大于一定数目的演员
     * @param number
     * @return
     */
    public JSONArray getGreaterActor(Integer number)
    {
        JSONArray temp=new JSONArray();
        List<person> persons=personrepository.findByActorNumGreaterThanEqualOrderByActorNumDesc(number);
        for(person temp1:persons)
        {
            JSONObject temp2=new JSONObject();
            temp2.put("演员名称",temp1.getName());
            temp2.put("参与电影数量",temp1.getDirectorNum());
            temp2.put("评分",temp1.getDirectorAverageScore());
            temp.add(temp2);
        }
        return temp;
    }

    /**
     * 根据人名查找合作演员情况
     * @param name
     * @return
     */
    public JSONArray getActorCompanion(String name)
    {
        return cooperationrepository.getCoActor(name);
    }

    /**
     * 根据人名查找合作演员情况
     * @param name
     * @return
     */
    public JSONArray getDirectorCompanion(String name)
    {
        return cooperationrepository.getCoDirector(name);
    }

}
