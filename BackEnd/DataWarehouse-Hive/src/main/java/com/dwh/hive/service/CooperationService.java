package com.dwh.hive.service;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dwh.hive.pojo.Cooperation;

public interface CooperationService extends IService<Cooperation> {

    JSONArray getActorCompanion(String name);

    JSONArray getDirectorCompanion(String name);

    JSONArray getCooperatorActor();

    JSONArray getCooperatorDirector();

    JSONArray getCooperatorAD();
}
