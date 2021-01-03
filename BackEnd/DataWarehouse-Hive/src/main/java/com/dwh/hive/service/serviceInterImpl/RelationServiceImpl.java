package com.dwh.hive.service.serviceInterImpl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dwh.hive.dao.RelationDAO;
import com.dwh.hive.pojo.Relation;
import com.dwh.hive.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelationServiceImpl
        extends ServiceImpl<RelationDAO, Relation> implements RelationService {

    @Autowired
    RelationDAO relationDAO;

    /**
     *查找电影参演的演员或者导演
     */
    public JSONArray getDirectorOrActor(String title, char identity)
    {
        /**
         * 自定义处理特殊排序
         */
        int j=0;
        JSONArray result = new JSONArray();
        if(identity=='A')
        {
            List<String> temp=relationDAO.getAssociateActor(title,'T');
            //temp.sort(temp,new Comparator<String>());
            int i=1;
            for(String t : temp){
                if(i<=50)
                {
                    JSONObject alpha=new JSONObject();
                    alpha.put("N",i);
                    alpha.put("actor",t);
                    result.add(alpha);
                }
                i++;
            }
            j=i;
        }
        else if(identity=='D')
        {
            List<String> temp=relationDAO.getAssociateDirector(title,'T');
            //temp.sort();
            int i=1;
            for(String t : temp){
                if(i<=50)
                {
                    JSONObject alpha=new JSONObject();
                    alpha.put("N",i);
                    alpha.put("director",t);
                    result.add(alpha);
                }
                i++;
            }
            j=j+i;
        }
        JSONObject a=new JSONObject();
        a.put("总查询数量",j);
        result.add(a);
        return result;
    }
}
