package com.example.demo.dao;

import com.alibaba.fastjson.JSONArray;
import com.example.demo.Entity.cooperation;
import com.example.demo.Entity.cooperationPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface cooperationRepository extends JpaRepository<cooperation, cooperationPK>,
        JpaSpecificationExecutor<cooperation> {

    @Query(value = "with index as ( select s.personID from" +
            " person s where s.name=?1)" +
            " select s.name,c.cooperateNum from person s " +
            "join cooperation c on s.personID = c.rightPersonID" +
            " where c.leftPersonID=index.personID AND s.actorOrDirector='D'" +
            " order by c.cooperateNum DESC",nativeQuery = true)
    JSONArray getCoDirector(String name);


    @Query(value = "with index as ( select s.personID from" +
            " person s where s.name=?1)" +
            " select s.name,c.cooperateNum from person s " +
            "join cooperation c on s.personID = c.rightPersonID" +
            " where c.leftPersonID=index.personID AND s.actorOrDirector='A'" +
            " order by c.cooperateNum DESC",nativeQuery = true)
    JSONArray getCoActor(String name);

}
