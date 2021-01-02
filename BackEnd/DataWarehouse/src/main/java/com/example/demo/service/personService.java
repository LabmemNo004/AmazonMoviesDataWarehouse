package com.example.demo.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Entity.cooperation;
import com.example.demo.Entity.person;
import com.example.demo.dao.cooperationRepository;
import com.example.demo.dao.personRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class personService {
    @Autowired
    private personRepository personrepository;

    @Autowired
    private cooperationRepository cooperationrepository;

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
        JSONArray temp=personrepository.getMoviesByDirector(name);
        int some=temp.size();
        JSONObject temp1=new JSONObject();
        temp1.put("电影数量",some);
        temp1.put("电影列表",temp);
        return temp1;
    }

    /**
     * 根据演员名找到他参加的电影。
     * @param name
     * @return
     */
    public JSONArray getActorWork(String name)
    {
        List<Map<String, Float>> temp=personrepository.getMoviesByActor(name);
        JSONArray temp0=new JSONArray();
        int i=1;
        for(Map<String, Float> temp2:temp){
            JSONObject temp1=new JSONObject();
            temp1.put("N",i);
            temp1.put("title",temp2.get("title"));
            temp1.put("score",temp2.get("score"));
            temp0.add(temp1);
            i++;
        }

        return temp0;
    }

    /**
     * 查找拍摄电影数目大于一定数目的导演
     * @param number
     * @return
     */
    public JSONArray getGreaterDirector(Integer number)
    {
        JSONArray temp=new JSONArray();

        List<person> persons=personrepository.
                findByDirectorNumGreaterThanEqualAndActorEitherDirectorOrderByDirectorNumDesc(number,'D');

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
     * 查找参加电影数目大于一定数目的演员
     * @param number
     * @return
     */
    public JSONArray getGreaterActor(Integer number)
    {
        JSONArray temp=new JSONArray();

        List<person> persons=personrepository.
                findByActorNumGreaterThanEqualAndActorEitherDirectorOrderByActorNumDesc(number,'A');

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
        List<Map<String,String>> temp=cooperationrepository.getCoActor(name);
        JSONArray temp1=new JSONArray();
        int i=1;
        for(Map<String,String> t:temp){
            JSONObject temp2=new JSONObject();
            temp2.put("N",i);
            temp2.put("name",t.get("name"));
            temp2.put("cooperationNum",t.get("Num"));
            temp1.add(temp2);
            i++;
        }
        return temp1;
    }

    /**
     * 根据人名查找合作导演情况
     * @param name
     * @return
     */
    public JSONArray getDirectorCompanion(String name)
    {
        List<Map<String,String>> temp=cooperationrepository.getCoDirector(name);
        JSONArray temp1=new JSONArray();
        int i=1;
        for(Map<String,String> t:temp){
            JSONObject temp2=new JSONObject();
            temp2.put("N",i);
            temp2.put("name",t.get("name"));
            temp2.put("cooperationNum",t.get("Num"));
            temp1.add(temp2);
            i++;
        }
        return temp1;
    }

    /**
     * 查找经常合作的演员
     * @return
     */
    public JSONArray getCooperatorActor()
    {
        JSONArray temp1=new JSONArray();
        List<cooperation> temp=cooperationrepository.getCoopratorActor();
        int i=0;
        for(cooperation x:temp)
        {
            i++;
            JSONObject y=new JSONObject();
            y.put("演员1",x.getLeftPersonName());
            y.put("演员2",x.getRightPersonName());
            y.put("合作次数",x.getCooperateNum());
            y.put("N",i);
            temp1.add(y);
        }

        return temp1;
    }

    /**
     * 查找经常合作的导演
     * @return
     */
    public JSONArray getCooperatorDirector()
    {
        JSONArray temp1=new JSONArray();
        List<cooperation> temp=cooperationrepository.getCoopratorDirector();

        int i=0;
        for(cooperation x:temp)
        {
            i++;
            JSONObject y=new JSONObject();
            y.put("导演1",x.getLeftPersonName());
            y.put("导演2",x.getRightPersonName());
            y.put("合作次数",x.getCooperateNum());
            y.put("N",i);
            temp1.add(y);
        }
        return temp1;
    }

    /**
     * 查找经常合作的演员和导演组合
     * @return
     */
    public JSONArray getCooperatorAD()
    {
        JSONArray temp1=new JSONArray();
        List<cooperation> temp=cooperationrepository.getCoopratorAD();
        int i=0;
        for(cooperation x:temp)
        {
            i++;
            JSONObject y=new JSONObject();
            y.put("演员",x.getLeftPersonName());
            y.put("导演",x.getRightPersonName());
            y.put("合作次数",x.getCooperateNum());
            y.put("N",i);
            temp1.add(y);
        }
        List<cooperation> temp2=cooperationrepository.getCoopratorDA();
        i=0;
        for(cooperation x:temp2)
        {
            i++;
            JSONObject y=new JSONObject();
            y.put("导演",x.getLeftPersonName());
            y.put("演员",x.getRightPersonName());
            y.put("合作次数",x.getCooperateNum());
            y.put("N",i);
            temp1.add(y);
        }

        return temp1;
    }





}
