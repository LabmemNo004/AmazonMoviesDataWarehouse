package com.dwh.hive.service.serviceInterImpl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dwh.hive.dao.CooperationDAO;
import com.dwh.hive.dao.PersonDAO;
import com.dwh.hive.pojo.Cooperation;
import com.dwh.hive.pojo.Person;
import com.dwh.hive.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PersonServiceImpl extends ServiceImpl<PersonDAO, Person> implements PersonService {
    @Autowired
    private PersonDAO personDAO;

    /**
     * 根据导演名找到他指导的电影。
     */
    public JSONObject getDirectorWork(String name)
    {
        List<Map<String,Float>> temp=personDAO.getMoviesByDirector(name);
        int some=temp.size();
        JSONArray b=new JSONArray();
        JSONObject temp1=new JSONObject();
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
        temp1.put("电影数量",some);
        temp1.put("电影列表",b);
        return temp1;
    }

    /**
     * 根据演员名找到他参加的电影。
     */
    public JSONArray getActorWork(String name)
    {
        List<Map<String, Float>> temp=personDAO.getMoviesByActor(name);
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
     * 查找拍摄电影数目大于一定数目的导演
     */
    public JSONArray getGreaterDirector(Integer number)
    {
        JSONArray temp=new JSONArray();

        List<Person> persons=personDAO.
                getGreaterDirector(number,'D');

        long i=1;
        for(Person temp1:persons)
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

        List<Person> persons=personDAO.
                getGreaterActor(number,'A');

        long i=0;
        for(Person temp1:persons)
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


}
