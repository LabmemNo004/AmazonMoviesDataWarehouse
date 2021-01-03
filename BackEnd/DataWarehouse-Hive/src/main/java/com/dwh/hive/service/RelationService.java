package com.dwh.hive.service;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dwh.hive.pojo.Relation;

public interface RelationService extends IService<Relation> {

    JSONArray getDirectorOrActor(String title, char identity);
}
