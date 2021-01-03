package com.dwh.hive.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dwh.hive.pojo.Person;

public interface PersonService extends IService<Person> {

    JSONObject getDirectorWork(String name);

    JSONArray getActorWork(String name);

    JSONArray getGreaterDirector(Integer number);

    JSONArray getGreaterActor(Integer number);
}
