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
    public JSONArray getDirectorWork(String name)
    {
        List<Map<String,Float>> temp=personrepository.getMoviesByDirector(name);
        int some=temp.size();
        JSONArray b=new JSONArray();

        long i=0;
        for(Map<String,Float> temp2:temp)
        {
            ++i;
            if(i<=50)
            {
                JSONObject a=new JSONObject();
                a.put("名称",temp2.get("title"));
                a.put("评分",temp2.get("score"));
                a.put("N",i);
                b.add(a);
            }
        }
        JSONObject temp1=new JSONObject();
        temp1.put("总查询数量",i);
        b.add(temp1);
        return b;
    }

    /**
     * 根据导演名找到他指导的电影。
     * @param name
     * @return
     */
    public JSONArray getDirectorWorkLike(String name)
    {
        List<Object []> temp=
                personrepository.getMoviesByDirectorLike(name);
        int some=temp.size();
        JSONArray b=new JSONArray();

        long i=0;
        for(Object [] temp2:temp)
        {
            ++i;
            if(i<=50)
            {
                JSONObject a=new JSONObject();
                String[] tag={"title","score","name"};
                for(int j=0;j<temp2.length;j++)
                {
                    a.put(tag[j],temp2[j]);
                }
                a.put("N",i);
                b.add(a);
            }
        }
        JSONObject temp1=new JSONObject();
        temp1.put("总查询数量",i);
        b.add(temp1);
        return b;
    }


    /**
     * 根据演员名找到他参加的电影。
     * @param name
     * @return
     */
    public JSONArray getActorWork(String name)
    {
        List<Map<String, Float>> temp=personrepository.getMoviesByActor(name);
        int some=temp.size();
        JSONArray temp0=new JSONArray();
        long i=1;
        for(Map<String, Float> temp2:temp){
            if(i<=50)
            {
                JSONObject temp1=new JSONObject();
                temp1.put("N",i);
                temp1.put("title",temp2.get("title"));
                temp1.put("score",temp2.get("score"));
                temp0.add(temp1);
            }
            i++;
        }
        JSONObject b=new JSONObject();
        b.put("总查询数量",some);
        temp0.add(b);
        return temp0;
    }

    /**
     * 根据演员名找到他参加的电影。
     * @param name
     * @return
     */
    public JSONArray getActorWorkLike(String name)
    {
        List<Object []> temp=
                personrepository.getMoviesByActorLike(name);
        int some=temp.size();
        JSONArray temp0=new JSONArray();
        long i=1;
        for(Object [] temp2:temp){
            if(i<=50)
            {
                JSONObject temp1=new JSONObject();
                temp1.put("N",i);
                String[] tag={"title","score","name"};
                for(int j=0;j<temp2.length;j++)
                {
                    temp1.put(tag[j],temp2[j]);
                }
                temp0.add(temp1);
            }
            i++;
        }
        JSONObject b=new JSONObject();
        b.put("总查询数量",some);
        temp0.add(b);
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
                getGreaterDirector(number,'D');

        long i=1;
        for(person temp1:persons)
        {
            if(i<=50)
            {
                JSONObject temp2=new JSONObject();
                temp2.put("N",i);
                temp2.put("导演名称",temp1.getName());
                temp2.put("参与电影数量",temp1.getDirectorNum());
                temp.add(temp2);
            }
            i++;
        }
        JSONObject b=new JSONObject();
        b.put("总查询数量",i);
        temp.add(b);
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
                getGreaterActor(number,'A');

        long i=0;
        for(person temp1:persons)
        {
            if(i<=50)
            {
                JSONObject temp2=new JSONObject();
                temp2.put("N",i);
                temp2.put("演员名称",temp1.getName());
                temp2.put("参与电影数量",temp1.getActorNum());
                temp.add(temp2);
            }
            i++;
        }
        JSONObject b=new JSONObject();
        b.put("总查询数量",i);
        temp.add(b);
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
        long i=1;
        for(Map<String,String> t:temp){
            if(i<=50)
            {
                JSONObject temp2=new JSONObject();
                temp2.put("N",i);
                temp2.put("name",t.get("name"));
                temp2.put("cooperationNum",t.get("Num"));
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
     * 根据人名查找合作导演情况
     * @param name
     * @return
     */
    public JSONArray getDirectorCompanion(String name)
    {
        List<Map<String,String>> temp=cooperationrepository.getCoDirector(name);
        JSONArray temp1=new JSONArray();
        long i=1;
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
        long i=0L;
        for(cooperation x:temp)
        {
            i++;
            if(i<=50)
            {
                JSONObject y=new JSONObject();
                y.put("演员1",x.getLeftPersonName());
                y.put("演员2",x.getRightPersonName());
                y.put("合作次数",x.getCooperateNum());
                y.put("N",i);
                temp1.add(y);
            }
        }
        JSONObject b=new JSONObject();
        b.put("总查询数量",i);
        temp1.add(b);
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

        long i=0;
        for(cooperation x:temp)
        {
            i++;
            if(i<=50)
            {
                JSONObject y=new JSONObject();
                y.put("导演1",x.getLeftPersonName());
                y.put("导演2",x.getRightPersonName());
                y.put("合作次数",x.getCooperateNum());
                y.put("N",i);
                temp1.add(y);
            }
        }
        JSONObject b=new JSONObject();
        b.put("总查询数量",i);
        temp1.add(b);
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
        long i=0L;
        for(cooperation x:temp)
        {
            i++;
            if(i<=50L)
            {
                JSONObject y=new JSONObject();
                y.put("演员",x.getLeftPersonName());
                y.put("导演",x.getRightPersonName());
                y.put("合作次数",x.getCooperateNum());
                y.put("N",i);
                temp1.add(y);
            }
        }
        List<cooperation> temp2=cooperationrepository.getCoopratorDA();
        long j=0L;
        for(cooperation x:temp2)
        {
            i++;
            j++;
            if(j<=50L)
            {
                JSONObject y=new JSONObject();
                y.put("导演",x.getLeftPersonName());
                y.put("演员",x.getRightPersonName());
                y.put("合作次数",x.getCooperateNum());
                y.put("N",j + 50);
                temp1.add(y);
            }
        }
        JSONObject b=new JSONObject();
        b.put("总查询数量",i);
        temp1.add(b);
        return temp1;
    }





}
