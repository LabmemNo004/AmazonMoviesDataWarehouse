package com.dwh.hive.service.serviceInterImpl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dwh.hive.dao.CooperationDAO;
import com.dwh.hive.pojo.Cooperation;
import com.dwh.hive.service.CooperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CooperationServiceImpl
        extends ServiceImpl<CooperationDAO, Cooperation> implements CooperationService {

    @Autowired
    CooperationDAO cooperationDAO;

    /**
     * 根据人名查找合作演员情况
     */
    public JSONArray getActorCompanion(String name)
    {
        List<Map<String,String>> temp=cooperationDAO.getCoActor(name);
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
     */
    public JSONArray getDirectorCompanion(String name)
    {
        List<Map<String,String>> temp=cooperationDAO.getCoDirector(name);
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
     */
    public JSONArray getCooperatorActor()
    {
        JSONArray temp1=new JSONArray();
        List<Cooperation> temp=cooperationDAO.getCoopratorActor();
        long i=0L;
        for(Cooperation x:temp)
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
     */
    public JSONArray getCooperatorDirector()
    {
        JSONArray temp1=new JSONArray();
        List<Cooperation> temp=cooperationDAO.getCoopratorDirector();

        long i=0;
        for(Cooperation x:temp)
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
     */
    public JSONArray getCooperatorAD()
    {
        JSONArray temp1=new JSONArray();
        List<Cooperation> temp=cooperationDAO.getCoopratorAD();
        long i=0L;
        for(Cooperation x:temp)
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
        List<Cooperation> temp2=cooperationDAO.getCoopratorDA();
        long j=0L;
        for(Cooperation x:temp2)
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
